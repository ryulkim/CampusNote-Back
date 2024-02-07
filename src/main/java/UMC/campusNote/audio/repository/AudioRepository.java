package UMC.campusNote.audio.repository;

import UMC.campusNote.audio.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {
}
