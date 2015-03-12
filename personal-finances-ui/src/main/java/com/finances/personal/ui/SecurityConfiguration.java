package com.finances.personal.ui;

import static org.springframework.boot.autoconfigure.security.SecurityProperties.ACCESS_OVERRIDE_ORDER;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@Order(ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	public static final String XSRF_TOKEN = "XSRF-TOKEN";
	public static final String X_XSRF_TOKEN = "X-" + XSRF_TOKEN;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.and()
			.logout()
			.and()
			.authorizeRequests()
			.antMatchers("/*", "/template/*", "/directive/*")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf()
			.csrfTokenRepository(csrfTokenRepository())
			.and()
			.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
	}
	
	private Filter csrfHeaderFilter() {
		return new CsrfHeaderFilter();
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(X_XSRF_TOKEN);

		return repository;
	}
	
}
