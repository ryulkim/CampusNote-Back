package UMC.campusNote.lessonNote.converter;

import UMC.campusNote.lessonNote.dto.LessonNoteRequestDTO;
import UMC.campusNote.lessonNote.dto.LessonNoteResponseDTO;
import UMC.campusNote.lessonNote.entity.LessonNote;
import UMC.campusNote.lessonNote.repository.LessonNoteRepository;
import UMC.campusNote.note.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonNoteConverter {
    public static LessonNoteResponseDTO.lessonNoteResultDTO toLessonNoteResultDTO(LessonNote lessonNote){
        return LessonNoteResponseDTO.lessonNoteResultDTO.builder()
                .lessonNoteId(lessonNote.getId())
                .noteId(lessonNote.getNote().getId())
                .build();
    }

    public static LessonNote toLessonNote(Note note){
        return LessonNote.builder()
                .note(note)
                .build();
    }

}
