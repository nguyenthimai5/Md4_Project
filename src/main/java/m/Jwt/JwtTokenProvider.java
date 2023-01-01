package m.Jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${m.jwt.secret}")
    private String JWT_SECRET;
    @Value("${m.jwt.expiration}")
    private int JWT_EXPIRATION;

    public String generateToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime()+JWT_EXPIRATION);
        return Jwts.builder().setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET).compact();
    }
    public String getUserNameFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String autoken){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(autoken);
            return true;
        }catch (MalformedJwtException ex){
            log.error("Mã không hợp lệ!");
        }catch (ExpiredJwtException ex){
            log.error("Mã đã hết hạn!!");
        }catch (UnsupportedJwtException ex){
            log.error("Hệ thống không hỗ trợ mã này!!");
        }catch (IllegalArgumentException ex){
            log.error("JWT yêu cầu đang rỗng!!");
        }
        return false;
    }
}
