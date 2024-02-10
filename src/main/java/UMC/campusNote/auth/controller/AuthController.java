package UMC.campusNote.auth.controller;

import UMC.campusNote.auth.dto.*;
import UMC.campusNote.auth.service.AuthService;
import UMC.campusNote.common.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static UMC.campusNote.common.code.status.SuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ApiResponse<AuthResponseDTO.JoinResDto> join(@RequestBody @Valid AuthRequestDTO.JoinReqDto joinReqDto){
        return ApiResponse.of(USER_JOIN, authService.join(joinReqDto));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO.LoginResDto> login(@RequestBody @Valid AuthRequestDTO.LoginReqDto loginReqDto){
        return ApiResponse.of(USER_LOGIN, authService.login(loginReqDto));
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponseDTO.RefreshResDto> refreshToken(HttpServletRequest request, HttpServletResponse response)  {
        return ApiResponse.of(ACCESS_TOKEN, authService.refreshToken(request, response));
    }

}
