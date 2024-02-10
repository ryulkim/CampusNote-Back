package UMC.campusNote.auth.service;

import UMC.campusNote.auth.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {


    AuthResponseDTO.JoinResDto join(AuthRequestDTO.JoinReqDto joinReqDto);

    AuthResponseDTO.LoginResDto login(AuthRequestDTO.LoginReqDto loginReqDto);

    AuthResponseDTO.RefreshResDto refreshToken(HttpServletRequest request, HttpServletResponse response);
}
