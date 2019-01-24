package com.salon.service.exception;

public class ExceptionCustom {
    private String code;
    private String type;
    private String messege;

    public ExceptionCustom() {
    }

    public ExceptionCustom(String code, String type, String messege) {
        this.code = code;
        this.type = type;
        this.messege = messege;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }
}
