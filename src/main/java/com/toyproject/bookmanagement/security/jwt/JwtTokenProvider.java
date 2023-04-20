package com.toyproject.bookmanagement.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.toyproject.bookmanagement.dto.auth.JwtRespDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
//	jwt는 key값이 중요
	
	private final Key key;
							//yml에서 가져옴
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secrertKey) {
		 						//byte배열 리턴
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secrertKey));
	}
	
	public JwtRespDto createToken(Authentication authentication) { // 유저정보를 가지고 토큰생성. 매개변수로 Authentication들어와야한다.
		
		StringBuilder stringBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(authority -> {
			stringBuilder.append(authority.getAuthority() + ",");
		});
		
		stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()); // stringBuilder 전체길이의 끝부터 그전까지 삭제 (슬라이

		String authorities = stringBuilder.toString();
		
		Date tokenExpiresDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24)); // 현재시간 + 하루 (1000 == 1초)
		
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName()) //authentication.getName() == login 할 수 있는 정보 //토큰의 제목(이름)
				.claim("auth", authorities) // authority
				.setExpiration(tokenExpiresDate) //토큰의 만료기간.
				.signWith(key, SignatureAlgorithm.HS256) // 토큰(key) 암호화
				.compact();
		
		return JwtRespDto.builder().grantType("Bearer").accessToken(accessToken).build();
		
	}	
	
}
