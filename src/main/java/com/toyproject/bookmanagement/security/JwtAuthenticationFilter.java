package com.toyproject.bookmanagement.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{

	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
		HttpServletRequest httpRequest = (HttpServletRequest) request; // 다운캐스팅
		String accessToken = httpRequest.getHeader("Authorization"); //기존토큰 가지고옴
		accessToken = jwtTokenProvider.getToken(accessToken);
		boolean validationFlag = jwtTokenProvider.vaildateToken(accessToken); //유효성 검사
		
		if(validationFlag) { //토큰을 받아서 SCH에 전달.
			Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
			SecurityContextHolder.getContext().setAuthentication(authentication); 
		}
		
		chain.doFilter(request, response);
	
	}

}
