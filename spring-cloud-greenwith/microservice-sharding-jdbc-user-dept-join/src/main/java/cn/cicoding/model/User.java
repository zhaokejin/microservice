package cn.cicoding.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -1205226416664488559L;
	
	private Long id;
	private String password;
	private String username;
	private Date createTime;
	private Date updateTime;
	private UserItem userItem;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public UserItem getUserItem() {
		return userItem;
	}

	public void setUserItem(UserItem userItem) {
		this.userItem = userItem;
	}
}