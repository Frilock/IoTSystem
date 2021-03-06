package org.iot.core.security;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class TokenUtil {

    @Getter
    private static final String TOKEN_HEADER = "authorization";
    @Getter
    private static final String TOKEN_PREFIX = "Token ";
    @Getter
    private static final long TOKEN_VALIDITY_TIME = Duration.ofHours(24).toMillis();

    @Value("${JWT_SECRET}")
    private String secretKey;

    public String createToken(String email) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY_TIME))
                .setSubject(email)
                .claim("role", "user")
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    Optional<Authentication> validateToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
                        .getBody();

                String username = claims.getSubject();
                if (StringUtils.hasText(username)) {
                    return Optional.of(new UsernamePasswordAuthenticationToken(
                            username,
                            token.replace(TOKEN_PREFIX, "").trim(),
                            null
                    ));
                }
            } catch (SignatureException ex) {
                log.error("Invalid JWT signature");
            } catch (MalformedJwtException ex) {
                log.error("Invalid JWT token");
            } catch (ExpiredJwtException ex) {
                log.error("Expired JWT token");
            } catch (UnsupportedJwtException ex) {
                log.error("Unsupported JWT token");
            } catch (IllegalArgumentException ex) {
                log.error("JWT claims string is empty.");
            }
        }
        return Optional.empty();
    }
}
