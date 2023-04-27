package com.toyproject.bookmanagement.dto.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalListRespDto {
	private int bookListId;
	private String bookName;
	private boolean rentalStatus;
}
