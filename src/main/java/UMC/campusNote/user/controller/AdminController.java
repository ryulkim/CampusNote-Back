package UMC.campusNote.user.controller;

import UMC.campusNote.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    public ApiResponse<String> test(){
        return ApiResponse.onSuccess("admin");
    }
}
