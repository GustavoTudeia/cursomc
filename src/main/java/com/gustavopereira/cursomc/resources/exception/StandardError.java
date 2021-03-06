package com.gustavopereira.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timeStamp;
	private String cause;
	
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
	
	public StandardError(Integer status, String msg, Long timeStamp, String cause) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
		this.cause = cause;
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

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
}
