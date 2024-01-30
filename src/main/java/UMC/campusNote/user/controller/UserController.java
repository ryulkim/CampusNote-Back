package UMC.campusNote.user.controller;

import UMC.campusNote.common.ApiResponse;

import UMC.campusNote.user.dto.JoinReqDto;
import UMC.campusNote.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @PostMapping("/signUp")
    public ApiResponse join(JoinReqDto joinReqDto){
        //예외 처리 필요(동시 가입 할 경우, 빈 값일 경우 등등등)

        userService.join(joinReqDto);

        return ApiResponse.onSuccess(null);
    }

}
