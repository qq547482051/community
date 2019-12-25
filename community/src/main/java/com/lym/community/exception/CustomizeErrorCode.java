package com.lym.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "所查找问题不存在!"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复!"),
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试!"),
    SYS_ERROR(2004, "机房没开空调,服务器炸了!"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在!"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在!"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    ;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

        CustomizeErrorCode(Integer code, String message) {
            this.message = message;
            this.code = code;
        }
    }