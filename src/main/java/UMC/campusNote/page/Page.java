package UMC.campusNote.page;

import UMC.campusNote.common.BaseEntity;
import UMC.campusNote.note.Note;
import UMC.campusNote.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAGE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    private String handWritingSVG; // 필기 svg

    private Integer pageNumber; // 페이지 넘버

    private String sideNote;

    private Integer round; // 회차
}
