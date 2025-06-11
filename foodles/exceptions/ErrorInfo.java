package com.cg.foodles.exceptions;

import java.time.LocalDateTime;

public class ErrorInfo {

	LocalDateTime timestamp;
	String msg;
	String url;
	
	public ErrorInfo() {
		super();
	}
	
	public ErrorInfo(LocalDateTime timestamp, String msg, String url) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.url = url;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "ErrorInfo [timestamp=" + timestamp + ", msg=" + msg + ", url=" + url + "]";
	}

}
