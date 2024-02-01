package com.cicoding.config.web.security;

import com.cicoding.filter.qq.QQAuthenticationFilter;
import com.cicoding.filter.qq.QQAuthenticationManager;
import com.cicoding.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/***
 * web安全配置
 * 
 * @author cicoding.cn
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Bean("userPasswordEncoder")
	public PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 定义认证信息提供者
		CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider(userService,
				passwordEncoder);
		auth.authenticationProvider(authenticationProvider).eraseCredentials(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// rememberme token 持久化仓库
		// PersistentTokenRepository tokenRepository = new
		// RedissonTokenRepositoryImpl(redissonClient);
		
		// 配置表单登录
		http.formLogin().loginPage("/login").loginProcessingUrl("/login");
		// 配置登出
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
		// 禁用csrf
		http.csrf().disable();
		// 配置 rememberme功能
		http.rememberMe().rememberMeParameter("rememberMe").alwaysRemember(false).userDetailsService(userService).key("fsafasfafafa-fafafa");
		// .tokenRepository(tokenRepository);
		// http.rememberMe().rememberMeServices(rememberMeServices);
		// 首页不允许直接访问
		// http.authorizeRequests().antMatchers("/").authenticated();
		// 其他允许访问
//		http.authorizeRequests().antMatchers("/**").permitAll();


		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/**").hasRole("USER")
				.and()
				.formLogin().loginPage("/login").defaultSuccessUrl("/user")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
		// 在 UsernamePasswordAuthenticationFilter 前添加 QQAuthenticationFilter
		http.addFilterAt(qqAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	/**
	 * 自定义 QQ登录 过滤器
	 */
	private QQAuthenticationFilter qqAuthenticationFilter(){
		QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/login/qq");
		SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
		successHandler.setAlwaysUseDefaultTargetUrl(true);
		successHandler.setDefaultTargetUrl("/user");
		authenticationFilter.setAuthenticationManager(new QQAuthenticationManager());
		authenticationFilter.setAuthenticationSuccessHandler(successHandler);
		return authenticationFilter;
	}
	
}
