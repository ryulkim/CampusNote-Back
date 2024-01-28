package UMC.campusNote.user.utils;


import UMC.campusNote.common.exception.GeneralException;

import UMC.campusNote.common.exception.handler.ExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

import java.util.Date;

import static UMC.campusNote.user.service.UserService.secretKey;

public class JwtUtil {
    public static String getUserName(String token){
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("userName", String.class);
    }

    public static boolean isExpired(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
        }
        catch (ExpiredJwtException ex){
            return true;
        }
        catch (MalformedJwtException ex){
            return true;
        }
    }

    public static String createJwt(String userName, Long expiredMs){
        return Jwts.builder()
                .claim("userName", userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
