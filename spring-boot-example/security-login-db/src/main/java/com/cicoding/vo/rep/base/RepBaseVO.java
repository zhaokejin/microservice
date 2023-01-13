package com.cicoding.vo.rep.base;

/***
 * 接口返回信息wrapper
 * 
 * @author zhaokejin
 *
 * @param <T>
 */
public class RepBaseVO<T> {
	private Integer code;
	private String mes;
	private T data;
	
	public static RepBaseVO newIntance() {
		return new RepBaseVO<>();
	}
	
	public Integer getCode() {
		return code;
	}
	
	public RepBaseVO<T> setCode(ResultCode code) {
		this.code = code.getCode();
		return this;
	}
	
	public String getMes() {
		return mes;
	}
	
	public RepBaseVO<T> setMes(String mes) {
		this.mes = mes;
		return this;
	}
	
	public T getData() {
		return data;
	}
	
	public RepBaseVO<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	public RepBaseVO<T> setCommonFail() {
		this.code = ResultCode.COMMON_FAIL.getCode();
		this.mes = "请求失败";
		return this;
	}
	
	public RepBaseVO<T> setInfo(ResultCode code, String mes, T data) {
		this.code = code.getCode();
		this.mes = mes;
		this.data = data;
		return this;
	}
	
	public RepBaseVO<T> setInfo(ResultCode code, String mes) {
		this.code = code.getCode();
		this.mes = mes;
		this.data = null;
		return this;
	}
	
	public RepBaseVO<T> setCommonSuccess() {
		this.code = ResultCode.SUCCESS.getCode();
		this.mes = "请求成功";
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RepBaseVO [code=").append(code).append(", mes=").append(mes).append(", data=").append(data)
				.append("]");
		return builder.toString();
	}
	
}
