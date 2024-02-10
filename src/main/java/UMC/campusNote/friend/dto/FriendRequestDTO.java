package UMC.campusNote.friend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

public class FriendRequestDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class AddFriendReqDto {
        @NotNull
        private Long inviterUserId;
        @NotNull
        private Long invitedUserId;
    }
}
