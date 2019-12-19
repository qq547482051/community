package com.lym.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUSTION_NOT_FOUND("所查找问题不存在！");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
