package com.cicoding.vo.rep.base;

/***
 * 分页DTO
 *
 * @author cicoding.cn
 *
 * @param <T>
 */
public class RepPageVO<T> extends RepBaseVO<T> {
	
	private Integer totalPage;
	
	private Integer currentPage;
	
	private Integer totalResult;
	
	public Integer getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("RepPageVO [totalPage=").append(totalPage).append(", currentPage=").append(currentPage)
				.append(", totalResult=").append(totalResult).append("]");
		return builder.toString();
	}
	
}
