package UMC.campusNote.user.service;

import UMC.campusNote.common.exception.GeneralException;
import UMC.campusNote.common.exception.handler.ExceptionHandler;
import UMC.campusNote.user.utils.JwtUtil;
import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import static UMC.campusNote.common.code.status.ErrorStatus.TOKEN_EXPIRED;
import static UMC.campusNote.common.code.status.ErrorStatus.TOKEN_MALFORM;

@Service
public class UserService {

    public static SecretKey secretKey=Jwts.SIG.HS256.key().build();
    private Long expiredAtMs=1000*60L; //1000*60*60L;
    public String login(String userName, String password){
        //인증 과정 필요
        return JwtUtil.createJwt(userName, expiredAtMs);
    }
}
