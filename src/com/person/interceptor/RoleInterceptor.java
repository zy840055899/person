package com.person.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.person.util.Dict;

/** 角色检查拦截器，用于检查用户是否有角色管理权限。 */
public class RoleInterceptor extends HandlerInterceptorAdapter
{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception
	{
		List<Integer> moduleIds = (List<Integer>) request.getSession().getAttribute("userModules");
		for(int module : moduleIds)
		{
			if(module == Dict.Module.ROLE)
				return true;
		}
		response.sendRedirect(request.getContextPath() + "/login/noPower.do");
		return false;
	}
}
