package com.kosarka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kosarka.repository.UserRepository;
import com.kosarka.service.UserService;

@Configuration
@EnableWebSecurity
public class HelloWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
/*	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .httpBasic().and()
	      .authorizeRequests()
	        .antMatchers("/index.html", "/login.html", "/").permitAll().anyRequest()
	        .authenticated().and()
	      .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	  }
	}*/


	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserService(userRepository);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeRequests().antMatchers("/index.html", "/partials/**", "/css/**", "/js/**","/api/newuser", "/register").permitAll().and()
//		.authorizeRequests().anyRequest().authenticated()
//		.and().formLogin().loginPage("/login").permitAll()
//		.and().logout()
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//	.and()
//	 .csrf().csrfTokenRepository(csrfTokenRepository());
//	.csrf().disable();
		// @formatter:off
		http
			.httpBasic().and()
			.authorizeRequests()
				.antMatchers("/login", "/partials/**", "/css/**", "/js/**","/api/newuser", "/register").permitAll().and()
				.authorizeRequests().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll()
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
				.csrf().disable();
			    //.csrf().csrfTokenRepository(csrfTokenRepository());
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}