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

import java.util.List;

import static UMC.campusNote.common.code.status.SuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/audios")
public class AudioController {

    private final AudioService audioService;


    @GetMapping("/{noteId}/{audioId}")
    public ApiResponse<AudioResDto> getAudio(@PathVariable("noteId") Long noteId, @PathVariable("audioId") Long audioId) {
        return ApiResponse.of(AUDIO_GET_ONE, audioService.getAudio(audioId));
    }

    @GetMapping("/{noteId}")
    public ApiResponse<List<AudioResDto>> getAudios(@PathVariable("noteId") Long noteId) {
        return ApiResponse.of(AUDIO_GET_ALL, audioService.getAudios(noteId));
    }

    @PostMapping("/{noteId}")
    public ApiResponse<AudioResDto> uploadAudio(@AuthenticationPrincipal User user, @PathVariable("noteId") Long noteId, @RequestParam("audioFile") MultipartFile audioFile) {
        return ApiResponse.of(AUDIO_CREATE, audioService.saveAudio(new S3UploadRequest(user.getId(), "audios"), noteId, audioFile));
    }

    @DeleteMapping("/{audioId}")
    public ApiResponse<AudioResDto> deleteAudio(@PathVariable("audioId") Long audioId) {
        return ApiResponse.of(AUDIO_DELETE, audioService.deleteAudio(audioId));
    }
}
