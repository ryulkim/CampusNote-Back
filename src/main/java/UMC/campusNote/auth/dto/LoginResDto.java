package UMC.campusNote.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class LoginResDto {
    private String token;

    public static LoginResDto fromEntity(String token){
        return LoginResDto.builder()
                .token(token)
                .build();
    }
}
