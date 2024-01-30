package UMC.campusNote.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class JoinReqDto {
    private String clientId;
    private String name;
    private String university;
    private String semester;

    //에타 시간표 링크 뭐지?

}
