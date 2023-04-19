package com.toyproject.bookmanagement.service;

import org.springframework.stereotype.Service;

import com.toyproject.bookmanagement.dto.auth.SignupReqDto;
import com.toyproject.bookmanagement.entity.User;
import com.toyproject.bookmanagement.exception.CustomException;
import com.toyproject.bookmanagement.exception.ErrorMap;
import com.toyproject.bookmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
	
	private final UserRepository userRepository;

	public void checkDuplicatedEmail(String email) {
		if(userRepository.findUserByEmail(email) != null) {
			throw new CustomException("DuplicatedEmail 오류", ErrorMap.builder().put("email", "사용중").build());
		}
	}
	
	public void signup(SignupReqDto signupReqDto) {
		User userEntity = signupReqDto.toEntity();
		userRepository.saveUser(userEntity);
	}
	
	
}