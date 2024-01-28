package UMC.campusNote.user.controller;

import UMC.campusNote.common.ApiResponse;
import UMC.campusNote.common.exception.handler.ExceptionHandler;
import UMC.campusNote.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static UMC.campusNote.common.code.status.ErrorStatus.TOKEN_EXPIRED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public String login(){
        return userService.login("김률아", "1234");
    }

    @PostMapping("/test1")
    public String test1(Authentication authentication){
        return authentication.getName();
    }

    @GetMapping("/test2")
    public ApiResponse test2(){
        return ApiResponse.onSuccess("test2");
    }

}
