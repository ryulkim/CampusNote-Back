package UMC.campusNote.config;

import UMC.campusNote.user.service.OAuth2UserService;
import UMC.campusNote.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static UMC.campusNote.user.service.UserService.secretKey;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    private final UserService userService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.httpBasic(httpBasicConfigurer -> httpBasicConfigurer.disable())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.authorizeRequests()
                .requestMatchers("/api/v1/users/join", "/api/v1/users/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/v1/**").authenticated()
                .anyRequest().permitAll();

        http.sessionManagement(config ->
                config.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt 사용하는 경우 씀
        );

        http.addFilterBefore(new JwtFilter(userService, secretKey.toString()), UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login(oauth2Configurer->oauth2Configurer
                .loginPage("/login")
                .successHandler(successHandler())
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService)));
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User=(DefaultOAuth2User) authentication.getPrincipal();

            String id=defaultOAuth2User.getAttributes().get("id").toString();
            String body= """
                    {"id":"%s"}
                    """.formatted(id);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            PrintWriter writer=response.getWriter();
            writer.println(body);
            writer.flush();
        });
    }
}
