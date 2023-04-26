package com.toyproject.bookmanagement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.toyproject.bookmanagement.dto.book.CategoryRespDto;
import com.toyproject.bookmanagement.dto.book.GetBookRespDto;
import com.toyproject.bookmanagement.dto.book.SearchBookReqDto;
import com.toyproject.bookmanagement.dto.book.SearchBookRespDto;
import com.toyproject.bookmanagement.entity.User;
import com.toyproject.bookmanagement.repository.BookRepository;
import com.toyproject.bookmanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	
	public GetBookRespDto getBook(int bookId) {
		return bookRepository.getBook(bookId).toGetBookRespDto();
	}
	
	public Map<String, Object> searchBookRespDtos(SearchBookReqDto searchBookReqDto){
		List<SearchBookRespDto> list = new ArrayList<>();
		
		int index = (searchBookReqDto.getPage() -1) * 20;
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("categoryIds", searchBookReqDto.getCategoryIds());
		map.put("searchValue", searchBookReqDto.getSearchValue());
		bookRepository.searchBooks(map).forEach(book -> {
			list.add(book.toDto());
		});
		
		System.out.println(searchBookReqDto.getSearchValue());
		
		int totalCount = bookRepository.getTotalCount(map);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("totalCount", totalCount);
		responseMap.put("bookList", list);
		return responseMap;
	}
	
	public List<CategoryRespDto> getCategories(){
		
		List<CategoryRespDto> list = new ArrayList<>();
		
		bookRepository.getCategories().forEach(category -> {
			list.add(category.toDto());
		});
		return list;
	}
	public int getLikecount(int bookId) {
		return bookRepository.getLikeCount(bookId);	
		}

	public int getLikeStatus(int bookId) {
		Map<String, Object> map = new HashMap<>();
		map.put("bookId", bookId);
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); // email jwt 필터에서 홀더에 넣어놨음
		User userEntity = userRepository.findUserByEmail(email);
		
		map.put("userId", userEntity.getUserId());
		
		
		return bookRepository.getLikeStatus(map);
	}
}
