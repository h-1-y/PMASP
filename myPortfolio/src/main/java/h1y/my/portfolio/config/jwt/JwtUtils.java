package h1y.my.portfolio.config.jwt;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import h1y.my.portfolio.dto.MemberInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

// JWT 관련 메소드를 제공하는 클래스
@Slf4j
@Component
public class JwtUtils {

	private final Key key;
	private final long accessTokenExpTime;
	
	public JwtUtils(
					  @Value("${jwt.secret}") String secretKey
					, @Value("${jwt.expiration_time}") long accessTokenExpTime
				   ) {
		
		byte [] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessTokenExpTime = accessTokenExpTime;
		
	}
	
	/*
	 * Access Token 생성
	 */
	public String createAccessToken(MemberInfoDto infoDto) {
		return createToken(infoDto, this.accessTokenExpTime);
	}
	
	/*
	 * JWT 생성
	 */
	public String createToken(MemberInfoDto infoDto, long expireTime) {
		
		Claims claims = Jwts.claims();
		
		claims.put("id", infoDto.getId());
		claims.put("loginId", infoDto.getLoginId());
		claims.put("name", infoDto.getName());
		
		System.out.println("JwtUtils Class claims.toString() ============= " + claims.toString());
		
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime tokenValidity = now.plusSeconds(expireTime);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(Date.from(now.toInstant()))
				.setExpiration(Date.from(tokenValidity.toInstant()))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
	}
	
	/*
	 * JWT 생성
	 */
	public Long getMemberId(String token) {
		return parseClaims(token).get("id", Long.class);
	}
	
	/*
     * JWT Claims 추출
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
	
    /**
     * JWT 검증
     * @param token
     * @return IsValidate
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
    
}
