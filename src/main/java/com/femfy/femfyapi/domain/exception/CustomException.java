package com.femfy.femfyapi.domain.exception;

public class CustomException  extends RuntimeException {
	private static final long serialVersionUID = 5646764724137127617L;

	public CustomException(String error) {
        super(error);
    }
}

