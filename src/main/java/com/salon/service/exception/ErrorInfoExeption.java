package com.salon.service.exception;

public class ErrorInfoExeption extends RuntimeException {
    private String type;

    public ErrorInfoExeption(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
