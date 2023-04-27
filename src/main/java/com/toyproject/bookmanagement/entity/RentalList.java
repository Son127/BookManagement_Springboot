package com.toyproject.bookmanagement.entity;

import com.toyproject.bookmanagement.dto.book.RentalListRespDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalList {
	private int bookListId;
	private String bookName;
	private int userId;

	public RentalListRespDto toDto() {
		return RentalListRespDto.builder()
				.bookListId(bookListId)
				.bookName(bookName)
				.userId(userId)
				.rentalStatus(userId == 0) //false일 경우 렌탈 불가능
				.build();
	}
}
