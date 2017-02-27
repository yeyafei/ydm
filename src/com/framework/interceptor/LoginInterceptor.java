package com.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.framework.crud.bean.user.User;

/**
 * 登录拦截器
 * @author yyf
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)request.getSession().getAttribute("USER");
		if(user == null){
			response.sendRedirect("/login.do");
			return false;
		}
		request.setCharacterEncoding("UTF-8");
		return true;
	}
}	
