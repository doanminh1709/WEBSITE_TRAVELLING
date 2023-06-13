package com.example.travel.exceptions;

public class UploadImageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UploadImageException(String message) {
        super(message);
    }
}
