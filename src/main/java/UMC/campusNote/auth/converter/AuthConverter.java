package UMC.campusNote.auth.converter;

import UMC.campusNote.auth.dto.AuthRequestDTO;
import UMC.campusNote.auth.dto.AuthResponseDTO;
import UMC.campusNote.user.entity.User;

import static UMC.campusNote.user.entity.Role.USER;

public class AuthConverter {
    public static AuthResponseDTO.JoinResDto toJoinResDto(Long userId, String accesstoken, String refreshtoken){
        return AuthResponseDTO.JoinResDto.builder()
                .userId(userId)
                .accesstoken(accesstoken)
                .refreshtoken(refreshtoken)
                .build();
    }

    public static AuthResponseDTO.LoginResDto toLoginResDto(Long userId, String accessToken, String refreshToken){
        return AuthResponseDTO.LoginResDto.builder()
                .userId(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static AuthResponseDTO.RefreshResDto toRefreshResDto(String accessToken){
        return AuthResponseDTO.RefreshResDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public static User toUser(AuthRequestDTO.JoinReqDto joinReqDto){
        return User.builder()
                .clientId(joinReqDto.getClientId())
                .img(joinReqDto.getImg())
                .name(joinReqDto.getName())
                .role(USER)
                .university(joinReqDto.getUniversity())
                .currentSemester(joinReqDto.getSemester())
                .build();
    }


}
