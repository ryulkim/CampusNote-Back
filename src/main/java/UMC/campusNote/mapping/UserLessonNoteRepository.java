package UMC.campusNote.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLessonNoteRepository extends JpaRepository<UserLessonNote, Long> {
    // noteId로 userId 찾기
    @Query("SELECT ul.user.id FROM UserLessonNote uln JOIN uln.userLesson ul WHERE uln.note.id = :noteId")
    Long findUserIdByNoteId(@Param("noteId") Long noteId);
}
