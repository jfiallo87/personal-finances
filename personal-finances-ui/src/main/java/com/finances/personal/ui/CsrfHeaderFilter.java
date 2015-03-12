package com.finances.personal.ui;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		processCsrf(csrf, request, response);

		filterChain.doFilter(request, response);
	}

	private void processCsrf(CsrfToken csrf, HttpServletRequest request, HttpServletResponse response) {
		if (csrf != null) {
			Cookie cookie = WebUtils.getCookie(request, SecurityConfiguration.XSRF_TOKEN);
			String token = csrf.getToken();

			processCookie(cookie, token, response);
		}
	}

	private void processCookie(Cookie cookie, String token, HttpServletResponse response) {
		if (cookie == null || (token != null && !token.equals(cookie.getValue()))) {
			cookie = new Cookie(SecurityConfiguration.XSRF_TOKEN, token);
			cookie.setPath("/");

			response.addCookie(cookie);
		}
	}
	
}
