package com.tst.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//글제목, 글내용, 작성자, 작성일자, 방문횟수
// VO (Value Object)

@Getter
@Setter
@AllArgsConstructor
public class BoardVO {
	
	public BoardVO() {
		
	}
	
	private int boardId;
	private String title;
	private String content;
	private String writer;
	private String createDate;
	private int cnt;
	
	
}
