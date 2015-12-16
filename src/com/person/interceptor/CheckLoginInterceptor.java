package com.person.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.person.dao.EmpDao;
import com.person.entity.Emp;

/** 登录检查拦截器，用于检查用户是否已经登录。 */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter
{
	@Resource
	private EmpDao empDao;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception
	{
		// 从session中读取登录信息
		Emp emp = (Emp) request.getSession().getAttribute("emp");
		// 判断是否登录过
		if (emp == null)
		{
			// 没有登录过，将请求重定向到登录页
			response.sendRedirect(request.getContextPath() + "/login/toLogin.do");
			return false;
		} else
		{
			// TODO 登录成功：记录登录时间
			return true;
		}
	}
}
