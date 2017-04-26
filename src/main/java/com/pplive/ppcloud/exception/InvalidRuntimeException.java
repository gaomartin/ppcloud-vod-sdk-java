package com.pplive.ppcloud.exception;


public class InvalidRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 4777011887086274817L;
	
	private String reason;

	public InvalidRuntimeException(String msg) {
		this(msg, "");
	}
	
	public InvalidRuntimeException(String msg, Throwable cause) {
		this(msg, "", cause);
	}
	
	public InvalidRuntimeException(String msg, String reason) {
		super(msg);
		this.reason = reason;
	}
	
	public InvalidRuntimeException(String msg, String reason, Throwable cause) {
		super(msg, cause);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}
