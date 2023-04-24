package com.toyproject.bookmanagement.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchBookRespDto {
	private int bookId;
	private String bookName;
	private int authorId;
	private String authorName;
	private int publisherId;
	private String publisherName;
	private int categoryId;
	private String categoryName;
	private String coverImgUrl;
}
