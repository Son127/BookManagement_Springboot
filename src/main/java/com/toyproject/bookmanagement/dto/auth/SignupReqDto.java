package com.toyproject.bookmanagement.dto.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.toyproject.bookmanagement.entity.User;

import lombok.Data;

@Data
public class SignupReqDto {
	//email은 validation이 있음
	@Email
	private String email;
	
	// 비밀번호 정규식.
	// ?= : 앞쪽이 일치하는가 .모든 문자  * : 문자가 있거나 없거나 \\d : 모든숫자 {최소값 , 최대값} : 글자갯수
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
			message = "비밀번호는 영문자, 숫자, 특수문자를 포함하여 8 ~ 16자로 작성")
	private String password;
	
	@Pattern(regexp = "^[가-힣]{2,7}$",
			message = "이름은 한글이름만 작성 가능합니다.")
	private String name;
	
	public User toEntity() {
		return User.builder()
				.email(email)
				.password(new BCryptPasswordEncoder().encode(password))
				.name(name)
				.build();
	}


}
