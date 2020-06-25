package com.peterMoon.kakaoPay.config;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtTokenProvider {

	@Value("spring.jwt.secret")
	private String secretKey;
	
	@Autowired
    private UserDetailsService userDetailsService;

	private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	private long tokenValidMilisecond = 1000L * 10 * 60; // 토큰 유효 시간(10분)
	
    @PostConstruct
    protected void init() {
    	secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    
    // JWT 생성
    public String createToken(String userName, List<String> roles) {
    	Claims claims = Jwts.claims().setSubject(userName);
    	claims.put("roles", roles);
    	Date date = new Date();
    	
    	return Jwts.builder()
    		.setClaims(claims)
    		.setIssuedAt(date)
    		.setExpiration(new Date(date.getTime() + tokenValidMilisecond))
    		.signWith(SignatureAlgorithm.HS256, secretKey)
    		.compact();
    }
    
    // 인증 조회
	public Authentication getAuthentication(String token) {
    	UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUser(token));
    	return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    
	// 유효성 체크
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (SignatureException | MalformedJwtException e) { 
			// 서명 오류
			logger.info("SignatureException");
			return false;
		} catch (ExpiredJwtException e) { 
			// 유효 기간 만료
			logger.info("ExpiredJwtException");
			return false;
		} catch(Exception e) {
			SecurityContextHolder.clearContext();
			return false;
		}
	}
   
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("token");
	}
   
	// User 정보 조회
	private String getUser(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
    
}