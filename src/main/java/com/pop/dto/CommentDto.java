package com.pop.dto;

import javax.validation.constraints.NotNull;

public class CommentDto {
	@NotNull
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommentDto(String message) {
		super();
		this.message = message;
	}
}
