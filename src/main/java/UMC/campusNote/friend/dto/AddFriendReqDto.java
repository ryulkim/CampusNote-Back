package UMC.campusNote.friend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddFriendReqDto {
    @NotNull
    private Long inviterUserId;
    @NotNull
    private Long invitedUserId;
}
