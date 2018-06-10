package com.daohu.runlife.api.domain.model;

public class JsonResult<T> {
	private int code;
	private String msg;
	private T data;

	public static int SUCCESS_CODE = 200;
	public static int FAILURE_CODE = 100;
	public static int NO_LOGIN_CODE = -1;
	public JsonResult() {
		this.code = SUCCESS_CODE;
		this.msg = "";
	}
	public JsonResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public JsonResult(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public JsonResult(T data){
		this.code = SUCCESS_CODE;
		this.data = data;
	}

	/**
	 * @return the cdoe
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}
