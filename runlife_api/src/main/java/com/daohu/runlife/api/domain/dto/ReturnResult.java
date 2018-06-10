package com.daohu.runlife.api.domain.dto;

public class ReturnResult {
    public static final int SUCCESS_CODE = 200;
    public static final int FAILURE_CODE = 100;

    private static final ReturnResult SUCCESS = new ReturnResult(SUCCESS_CODE);
    private static final ReturnResult FAILURE = new ReturnResult(null);

    private int code;
    private String msg;
    private Object data;

    public ReturnResult(int code){
        this.code = code;
    }

    public ReturnResult(int code, Object data){
        this.code = code;
        this.data = data;
    }

    public ReturnResult(String msg){
        this.code = FAILURE_CODE;
        this.msg = msg;
    }

    public static ReturnResult success(){
        SUCCESS.data = null;
        return SUCCESS;
    }

    public static ReturnResult success(Object data){
        SUCCESS.data = data;
        return SUCCESS;
    }

    public static ReturnResult failure(String msg){
        FAILURE.msg = msg;
        return FAILURE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
