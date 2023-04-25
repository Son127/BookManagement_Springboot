package com.toyproject.bookmanagement.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRespDto {
	private int categoryId;
	private String categoryName;
}
