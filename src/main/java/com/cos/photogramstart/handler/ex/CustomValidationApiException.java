package com.cos.photogramstart.handler.ex;

import java.util.Map;

import lombok.Getter;

public class CustomValidationApiException extends RuntimeException {

	// 객체를 구분할 때(JVM)
	private static final long serialVersionUID = 1L;

	@Getter
	private Map<String, String> errorMap;

	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	}

	public CustomValidationApiException(String message) {
		super(message);
	}

}
