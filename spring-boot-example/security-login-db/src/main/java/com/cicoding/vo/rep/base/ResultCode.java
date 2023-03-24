package com.cicoding.vo.rep.base;

/***
 * 返回代码
 *
 * @author zhaokejin
 *
 */
public enum ResultCode {
	/** 一般成功 */
	SUCCESS(200, "成功"),
	/** 一般失败 */
	COMMON_FAIL(400, "失败"),
	/** 拒绝访问（权限不足） */
	ACCESS_DENIED(401, "权限不足"),;
	
	private Integer code;
	
	private String mes;
	
	private ResultCode(Integer code, String mes) {
		this.code = code;
		this.mes = mes;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMes() {
		return mes;
	}
	
	public void setMes(String mes) {
		this.mes = mes;
	}
	
}
