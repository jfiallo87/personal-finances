package com.finances.personal.ui.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/token")
public class TokenController {
	
	@RequestMapping(method=GET)
	public Map<String, String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}
	
}