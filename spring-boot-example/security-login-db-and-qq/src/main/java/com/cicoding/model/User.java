package com.cicoding.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/****
 * 会员
 * 
 * @author cicoding.cn
 *
 */
@Data
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -164337608917052650L;
	
	public static final List<SimpleGrantedAuthority> DEFAULT_GRANT_AUTHORITIES = new ArrayList<>(
			Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
	
	/***
	 * id
	 */
	private Long id;
	
	/***
	 * 用户名
	 */
	private String username;
	
	/***
	 * 密码
	 */
	private String password;
	
	/***
	 * email
	 */
	private String email;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 省份
	 */
	private String province;

	/**
	 * 出生年
	 */
	private String year;

	/**
	 * 头像
	 */
	private String avatar;
	
	/***
	 * 添加时间
	 */
	private Date addDate;
	
	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;
	
	private boolean credentialsNonExpired = true;
	
	private boolean enabled = true;
	
	private List<SimpleGrantedAuthority> authorities = DEFAULT_GRANT_AUTHORITIES;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}


}
