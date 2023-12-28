package com.cicoding.exception;

/***
 * 程序-运行时错误
 *
 * @author cicoding.cn
 *
 */
public class ApplicationRuntimeException extends RuntimeException {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -3607242177736229275L;
	
	public ApplicationRuntimeException(String mes) {
		super(mes);
	}
	
	public ApplicationRuntimeException() {
		super();
	}
	
	public ApplicationRuntimeException(String message, Throwable cause, boolean enableSuppression,
									   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ApplicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ApplicationRuntimeException(Throwable cause) {
		super(cause);
	}
	
}
