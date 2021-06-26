package com.milestoner.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * JWT library
 *
 * @author Jason
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JWTUtil {
  private String secretKey;
  private Integer lifeTime;

  public String sign(String userId) {
    Claims claims = Jwts.claims();

    // expiration
    LocalDateTime localDateTime = LocalDateTime.now();
    localDateTime.plusSeconds(lifeTime);
    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    Date date = Date.from(instant);

    claims.setExpiration(date);
    claims.put("userId", userId);

    Key secretKey = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    return Jwts.builder().setClaims(claims).signWith(secretKey, SignatureAlgorithm.HS256).compact();
  }

  public String verify(String token) {
    String userId;

    try {
      Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
      Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
      userId = claims.get("userId").toString();
    } catch (Exception e) {
      userId = null;
    }

    return userId;
  }
}
