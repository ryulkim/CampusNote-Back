package UMC.campusNote.config;

import UMC.campusNote.common.ApiResponse;
import UMC.campusNote.user.service.OAuth2UserService;
import UMC.campusNote.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

        //권한 넣는거 필요
        http.authorizeRequests()
                .requestMatchers("/api/v1/users/join", "/api/v1/users/login","/login").permitAll()
                .requestMatchers("/api/v1/**").authenticated()
                .anyRequest().permitAll();

        http.sessionManagement(config ->
                config.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt 사용하는 경우 씀
        );

        http.addFilterBefore(new JwtFilter(userService, secretKey.toString()), UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login(oauth2Configurer->oauth2Configurer
//                .loginPage("/login")
                .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig.baseUri("/api/vi/auth/oauth2")) //default는 /oauth2/authorization, 접근하고 싶으면 baseUri 뒤에 registerId 넣어야 함
                .successHandler(successHandler())
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService)));
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            DefaultOAuth2User defaultOAuth2User=(DefaultOAuth2User) authentication.getPrincipal();

            String id=defaultOAuth2User.getName();
            Object name= defaultOAuth2User.getAttribute("properties");

            log.info("nickname: {}",name.toString());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            String token = userService.login(id, id);

//            PrintWriter writer=response.getWriter();
//            writer.println("로그인 성공~~ id: "+id.toString());
//            writer.flush();

            response.sendRedirect("/auth/oauth2-response/"+token);
        });
    }
}
