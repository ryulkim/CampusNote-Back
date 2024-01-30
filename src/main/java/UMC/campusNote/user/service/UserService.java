package UMC.campusNote.user.service;

import UMC.campusNote.user.dto.JoinReqDto;
import UMC.campusNote.user.entity.User;
import UMC.campusNote.user.repository.UserRepository;
import UMC.campusNote.user.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import static UMC.campusNote.user.entity.Role.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private Long expiredAtMs=1000*60L; //1000*60*60L;


    public String createToken(String userName){
        //인증 과정 필요(userName과 password를 사용한 인증 구현 필요)
        return jwtUtil.createJwt(userName, expiredAtMs);
    }

    public boolean isPresentUser(String clientId){
        User user = userRepository.findByClientId(clientId);

        if(user==null) return false;

        return true;
    }

    public void join(JoinReqDto joinReqDto){

        //예외처리 필요, 이름이라던지 뭐 그런거라던지..

        User user1= User.builder()
                        .clientId(joinReqDto.getClientId())
                        .img("123123")
                        .name(joinReqDto.getName())
                        .role(USER.getRole())
                        .university("인하대")
                        .currentSemester("202")
                        .build();

        String token=createToken(joinReqDto.getName());

        //토큰 값 응답

        userRepository.save(user1);

    }
}
