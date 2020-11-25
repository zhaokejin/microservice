package cn.cicoding.cloud.user.entity;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 异常信息封装类
 * @author zhaokejin
 *
 */
public class ErrorInfo implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6619783175548428647L;
	/**
	 * 异常基本信息
	 */
    private List<String> message;
    /**
     * 带堆栈信息的详细错误信息
     */
    private String fullErrorStack;
    /**
     * 发生异常的URL
     */
    private String url;
    /**
     * 异常发生日期
     */
    private Date date;
	/**
	 * 异常的类型全名
	 */
	private String exception;
	/**
	 * 是否业务异常
	 */
    private Boolean bizError = false;
    /**
     * 异常发生日期
     * @return
     */
    public Date getDate() {
		return date;
	}
    /**
     * 异常发生日期
     * @param date
     */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 异常基本信息
	 * @return
	 */
	public List<String> getMessage() {
		return message;
	}
	/**
	 * 异常基本信息
	 * @param message
	 */
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	/**
	 * 异常基本信息
	 * @param msg
	 */
	public void addMessage(String msg) {
		if(message == null) {
			message = new ArrayList<String>();
		}
		this.message.add(msg);
	}
	/**
	 * 发生异常的URL
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 发生异常的URL
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 是否业务异常
	 * @return
	 */
	public Boolean getBizError() {
		return bizError;
	}
	/**
	 * 是否业务异常
	 * @param bizError
	 */
	public void setBizError(Boolean bizError) {
		this.bizError = bizError;
	}
	/**
	 * 带堆栈信息的详细错误信息
	 * @return
	 */
	public String getFullErrorStack() {
		return fullErrorStack;
	}
	/**
	 * 带堆栈信息的详细错误信息
	 * @param fullErrorStack
	 */
	public void setFullErrorStack(String fullErrorStack) {
		this.fullErrorStack = fullErrorStack;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
}
