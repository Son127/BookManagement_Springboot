package com.toyproject.bookmanagement.dto.book;

import lombok.Data;

@Data
public class SearchBookReqDto {
	
	private int page;
	private int categoryId;
	private String searchValue;
}
