package UMC.campusNote.friend.controller;

import UMC.campusNote.common.ApiResponse;
<<<<<<< HEAD
import UMC.campusNote.friend.dto.FriendRequestDTO;
import UMC.campusNote.friend.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
=======
import UMC.campusNote.friend.dto.AddFriendReqDto;
import UMC.campusNote.friend.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
>>>>>>> f48bd7b1b20b984b1b6589a058a71b1dac291212
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService friendService;
    @PostMapping
<<<<<<< HEAD
    private ApiResponse addFriend(@Valid @RequestBody FriendRequestDTO.AddFriendReqDTO addFriendReqDto){
=======
    private ApiResponse addFriend(@Valid @RequestBody AddFriendReqDto addFriendReqDto){
>>>>>>> f48bd7b1b20b984b1b6589a058a71b1dac291212

        friendService.addFriend(addFriendReqDto);

        return ApiResponse.onSuccess(null);
    }
}
