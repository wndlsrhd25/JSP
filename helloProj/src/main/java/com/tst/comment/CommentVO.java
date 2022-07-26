package com.tst.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentVO {

	public CommentVO() {
		
	}
	
	private int boardId;
	private int commentId;
	private String commentContent;
	private String userId;
	private String commentDate;
	
	
}
