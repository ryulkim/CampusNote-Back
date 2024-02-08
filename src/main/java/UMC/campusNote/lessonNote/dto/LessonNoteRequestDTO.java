package UMC.campusNote.lessonNote.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class LessonNoteRequestDTO {
    @Getter
    public static class LessonNoteDTO{
        @Schema(description = "노트아이디", example = "1")
        Long noteId;
    }
}
