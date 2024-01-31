package UMC.campusNote.auth.service;

import UMC.campusNote.auth.dto.LoginReqDto;
import UMC.campusNote.auth.dto.LoginResDto;
import UMC.campusNote.user.dto.JoinReqDto;
import UMC.campusNote.user.dto.JoinResDto;

public interface AuthService {

    String createToken(String userName);

    boolean isPresentUser(String clientId);
    JoinResDto join(JoinReqDto joinReqDto);

    LoginResDto login(LoginReqDto loginReqDto);
}
