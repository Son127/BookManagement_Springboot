package com.toyproject.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 요청들에게 크로스오리진 설정
				.allowedMethods("*") 
				.allowedOrigins("*"); // 3000번 포트에서 들어오는 
//				.allowedOrigins("http://localhost:3000"); // 3000번 포트에서 들어오는 
	}
}
