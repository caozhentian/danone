package com.danone.comfit.common.bean;

/**
 * 整个数据返回的类�?
 * 
 * @author lanyan
 * 
 * @param <T>
 *            result字段的类�?
 */
/**
 * @author Administrator
 *
 * @param <T>
 */
public class BaseModel<T> {
	protected String message;
	protected int code;
	protected T data;

	 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	 

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
