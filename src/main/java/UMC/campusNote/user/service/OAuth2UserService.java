package UMC.campusNote.user.service;

import UMC.campusNote.common.exception.handler.ExceptionHandler;
import UMC.campusNote.user.entity.User;
import UMC.campusNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;


import static UMC.campusNote.common.code.status.ErrorStatus.BAD_REQUEST;
import static UMC.campusNote.common.code.status.ErrorStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Role generate
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");

        // nameAttributeKey
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        log.warn(oAuth2User.getAttributes().toString());

        // DB 저장로직이 필요하면 추가
        String oauthClientName=userRequest.getClientRegistration().getClientName(); //registraion Id를 가져올 수 있음(kakao, naver...)
        String clientId=oAuth2User.getAttribute("id").toString();

        if(oauthClientName.equals("kakao")){
            log.info(clientId);
            User user = userRepository.findByClientId(clientId);

            //로그인/회원가입 분리? 아님 통합? => 일단 회원가입 되는 걸로 한다.
            if(user==null) {
                User user1= User.builder()
                        .clientId(clientId)
                        .img("1231")
                        .name("김률아")
                        .role("ㅁㅁㅁㅁ")
                        .university("인하대")
                        .currentSemester("202")
                        .build();
                userRepository.save(user1);
                //throw new ExceptionHandler(USER_NOT_FOUND);
            }

            log.info(user.getName());
            //userService.login(user.getName(),user.getName());

        }

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
    }
}
