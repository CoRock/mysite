package com.corock.mvc.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mvc.util.WebUtils;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** Cookie Test */
		// reading a cookie
		int count = 1;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if ("visitCount".equals(c.getName())) {
					count = Integer.parseInt(c.getValue());
					break;
				}
			}
		}
		
		count++;
		Cookie cookie = new Cookie("visitCount", "" + count);
		cookie.setMaxAge(24 * 60 * 60);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);

		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

}
