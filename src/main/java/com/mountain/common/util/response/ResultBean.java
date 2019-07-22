package com.mountain.common.util.response;


/**
 * 
* @Title: ResultBean.java 
* @Package com.slarts.common.util.response 
* @Description: TODO 统一返回格式
* @author porridge
* @date 2016年10月27日 下午10:06:55 
* @version V1.0
 */
public class ResultBean<T> {
	private Integer status;
	private String msg;
	private T data;
	
	public static ResultBean<Object> defaultSuccess(){
		return new ResultBean<>(GlobalStatusCode.CODE_200.code(), GlobalStatusCode.CODE_200.value());
	}

	public static <T> ResultBean<T> defaultSuccess(T data){
		return new ResultBean<>(GlobalStatusCode.CODE_200.code(), GlobalStatusCode.CODE_200.value(), data);
	}
	
	public ResultBean() {
		super();
	}

	public ResultBean(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public ResultBean(Integer status) {
		super();
		this.status = status;
	}
	public ResultBean(Integer status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
