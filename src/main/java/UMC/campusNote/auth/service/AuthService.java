package UMC.campusNote.auth.service;

import UMC.campusNote.auth.dto.JoinReqDto;
import UMC.campusNote.auth.dto.JoinResDto;
import UMC.campusNote.auth.dto.LoginReqDto;
import UMC.campusNote.auth.dto.LoginResDto;

public interface AuthService {


    JoinResDto join(JoinReqDto joinReqDto);

    LoginResDto login(LoginReqDto loginReqDto);
}
