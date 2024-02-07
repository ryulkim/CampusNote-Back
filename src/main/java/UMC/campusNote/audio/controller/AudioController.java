package UMC.campusNote.audio.controller;


import UMC.campusNote.audio.dto.AudioResDto;
import UMC.campusNote.audio.service.AudioService;
import UMC.campusNote.common.ApiResponse;
import UMC.campusNote.common.s3.dto.S3UploadRequest;
import UMC.campusNote.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static UMC.campusNote.common.code.status.SuccessStatus.AUDIO_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/audios")
public class AudioController {

    private final AudioService audioService;

    @PostMapping("/{noteId}")
    public ApiResponse<AudioResDto> uploadAudio(@AuthenticationPrincipal User user, @PathVariable("noteId") Long noteId, @RequestParam("audioFile") MultipartFile audioFile) {
        return ApiResponse.of(AUDIO_CREATE, audioService.saveAudio(new S3UploadRequest(user.getId(), "audios"), noteId, audioFile));
    }
}
