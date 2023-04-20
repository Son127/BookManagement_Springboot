package com.toyproject.bookmanagement.repository;

import org.apache.ibatis.annotations.Mapper;

import com.toyproject.bookmanagement.entity.Authority;
import com.toyproject.bookmanagement.entity.User;

@Mapper
public interface UserRepository {
	
	public User findUserByEmail(String email);
	public int saveUser(User user); // insert는 반환값 int
	public int saveAuthority(Authority authority);
}
