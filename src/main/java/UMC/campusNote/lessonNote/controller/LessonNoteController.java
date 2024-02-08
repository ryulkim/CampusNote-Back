package UMC.campusNote.lessonNote.controller;

import UMC.campusNote.common.ApiResponse;
import UMC.campusNote.lessonNote.converter.LessonNoteConverter;
import UMC.campusNote.lessonNote.dto.LessonNoteRequestDTO;
import UMC.campusNote.lessonNote.dto.LessonNoteResponseDTO;
import UMC.campusNote.lessonNote.entity.LessonNote;
import UMC.campusNote.lessonNote.service.LessonNoteService;
import UMC.campusNote.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import static UMC.campusNote.common.code.status.SuccessStatus.LESSONNOTE_CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessonNotes")
public class LessonNoteController {
    private final LessonNoteService lessonNoteService;
    @PostMapping
    @Operation(summary = "강의 노트 등록 API", description = "request 파라미터로 noteId // image 파라미터로 파일 넣기 ")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "LESSONNOTE201",description = "강의 노트 업로드 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTE4001", description = "존재하지 않는 노트.",content = @Content(schema = @Schema(implementation = ApiResponses.class))),
    })
    public ApiResponse<LessonNoteResponseDTO.lessonNoteResultDTO> create(@AuthenticationPrincipal User user, @RequestPart("request") LessonNoteRequestDTO.LessonNoteDTO request, @RequestPart("image") MultipartFile image) throws IOException {
        LessonNote newLessonNote = lessonNoteService.createLessonNote(request, image, user.getId());
        return ApiResponse.of(LESSONNOTE_CREATE, LessonNoteConverter.toLessonNoteResultDTO(newLessonNote));
    }
}
