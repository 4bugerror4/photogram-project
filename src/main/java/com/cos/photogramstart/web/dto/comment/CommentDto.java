package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	
	@NotBlank
	private String content;
	
	@NotNull
	private int imageId;

}
