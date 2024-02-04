package UMC.campusNote.auth.service;

import UMC.campusNote.auth.dto.JoinResDto;
import UMC.campusNote.auth.dto.JoinReqDto;
import UMC.campusNote.auth.dto.LoginReqDto;
import UMC.campusNote.auth.dto.LoginResDto;
import UMC.campusNote.common.exception.GeneralException;


import UMC.campusNote.user.entity.User;
import UMC.campusNote.user.repository.UserRepository;
import UMC.campusNote.user.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static UMC.campusNote.common.code.status.ErrorStatus.USER_ALREADY_EXIST;
import static UMC.campusNote.common.code.status.ErrorStatus.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //1000*60*60L;
    private final Long expiredAtMs=1000*60*60L;


    @Override
    public String createToken(String userName){
        //인증 과정 필요(userName과 password를 사용한 인증 구현 필요)
        return jwtUtil.createJwt(userName, expiredAtMs);
    }


    @Override
    public boolean isPresentUser(String clientId) {
        userRepository.findByClientId(clientId).ifPresent(user -> {
            throw new GeneralException(USER_ALREADY_EXIST);
        });
        return true;
    }

    @Override
    public JoinResDto join(JoinReqDto joinReqDto){

        isPresentUser(joinReqDto.getClientId());

        User newUser = joinReqDto.toEntity();
        log.info("clientId: "+newUser.getClientId());
        log.info("role: "+newUser.getRole());
        userRepository.save(newUser);


        String token=createToken(newUser.getClientId());

        return JoinResDto.fromEntity(token);
    }

    @Override
    public LoginResDto login(LoginReqDto loginReqDto){
        log.info("loginReqDto:{}",loginReqDto);
        User user = userRepository.findByClientId(loginReqDto.getClientId()).
                orElseThrow(() -> new GeneralException(USER_NOT_FOUND));
        log.info("user:{}",user.getName());
        return LoginResDto.fromEntity(createToken(user.getClientId()));
    }


}
