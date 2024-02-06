package UMC.campusNote.page.repository;

import UMC.campusNote.page.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
    Page findByNoteIdAndPageNumber(Long noteId, Integer pageNumber);
}
