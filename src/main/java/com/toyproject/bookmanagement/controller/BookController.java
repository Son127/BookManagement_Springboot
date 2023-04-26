package com.toyproject.bookmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.bookmanagement.dto.book.CategoryRespDto;
import com.toyproject.bookmanagement.dto.book.SearchBookReqDto;
import com.toyproject.bookmanagement.service.BookService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController { // c -> rep -> s
	
	private final BookService bookService;
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<?> getBook(@PathVariable int bookId){
		return ResponseEntity.ok().body(bookService.getBook(bookId));
	}
	
	@GetMapping("/books")
	public ResponseEntity<?> searchBooks(SearchBookReqDto searchBookReqDto) {
		return ResponseEntity.ok().body(bookService.searchBookRespDtos(searchBookReqDto));
	}
	
	@GetMapping("/categories")
	public ResponseEntity<?> categories(){
		return ResponseEntity.ok().body(bookService.getCategories());
	}
	
	@GetMapping("/book/{bookId}/like")
	public ResponseEntity<?> getLikeCount(@PathVariable int bookId){
		return ResponseEntity.ok().body(bookService.getLikecount(bookId));
	}
	
	
}
