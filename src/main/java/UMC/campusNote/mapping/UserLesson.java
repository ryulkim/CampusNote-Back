package UMC.campusNote.mapping;

import UMC.campusNote.lesson.Lesson;
import UMC.campusNote.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_LESSON_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "LESSON_ID")
    private Lesson lesson;

    private String attendedSemester; // 수강 학기

    public void setUserAndLesson(User user, Lesson lesson){
        // 예외 처리 필요
        this.user = user;
        this.lesson = lesson;

        user.getUserLessonList().add(this);
        lesson.getUserLessonList().add(this);
    }
}
