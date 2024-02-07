package UMC.campusNote.audio.dto;

import UMC.campusNote.audio.entity.Audio;
import UMC.campusNote.auth.dto.JoinResDto;
import UMC.campusNote.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static UMC.campusNote.user.entity.Role.USER;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AudioResDto {

    private Long audioId;
    public static AudioResDto fromEntity(Long audioId){
        return AudioResDto.builder()
                .audioId(audioId)
                .build();
    }
}
