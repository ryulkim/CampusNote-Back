package UMC.campusNote.page;

import UMC.campusNote.common.ApiResponse;
import UMC.campusNote.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pages")
public class PageController {
    private final PageService pageService;

    @Operation(summary = "페이지 필기 등록 API", description = "request 파라미터로 noteId, pageNum, sideNote, round // image 파라미터로 파일 넣기")
    @PostMapping
    public ApiResponse<PageResponseDTO.pageResultDTO> write(@AuthenticationPrincipal User user, @RequestPart("request") PageRequestDTO.PageDto request, @RequestPart("image") MultipartFile image) throws IOException {
        Page newPage = pageService.writePage(request, image, user.getId());
        return ApiResponse.onSuccess(PageConverter.toPageResultDTO(newPage));
    }
}
