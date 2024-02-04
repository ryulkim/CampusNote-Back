package UMC.campusNote.config;

import UMC.campusNote.auth.service.AuthServiceImpl;
import UMC.campusNote.user.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final AuthServiceImpl authServiceImpl;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String token = resolveToken(request);

            //Token validation 여부
            if (token==null||!jwtUtil.validToken(token)) {
                log.error("token이 유효하지 않습니다: {}",token);
                filterChain.doFilter(request, response);
                return;
            }

            //UserName Token에서 꺼내기
            String userName = jwtUtil.getUserName(token);
            log.info("userName:{}", userName);

            // 토큰이 유효하고 만료되지 않았다면 SecurityContext에 인증 정보를 저장
            // 토큰이 만료되지 않았는지는 JwtService에서 확인
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

                log.info(userDetails.getUsername());
                userDetails.getAuthorities().
                        stream()
                        .map(a-> {
                            log.info(a.getAuthority());
                            return null;
                        })
                        ;

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

//            //권한 부여 => 수정 필요
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));
//            //Detail을 넣어준다.
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        // token 안 보내면 Block
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }

        //Token 꺼내기
        return authorization.split(" ")[1];
    }

}
