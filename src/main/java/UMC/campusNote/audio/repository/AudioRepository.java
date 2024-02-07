package UMC.campusNote.audio.repository;

import UMC.campusNote.audio.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AudioRepository extends JpaRepository<Audio, Long> {

    List<Audio> findByNoteId(Long noteId);
}
