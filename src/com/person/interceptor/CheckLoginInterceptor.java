package com.person.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.person.dao.EmpDao;
import com.person.entity.Emp;

/** ��¼��������������ڼ���û��Ƿ��Ѿ���¼�� */
public class CheckLoginInterceptor extends HandlerInterceptorAdapter
{
	@Resource
	private EmpDao empDao;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception
	{
		// ��session�ж�ȡ��¼��Ϣ
		Emp emp = (Emp) request.getSession().getAttribute("emp");
		// �ж��Ƿ��¼��
		if (emp == null)
		{
			// û�е�¼�����������ض��򵽵�¼ҳ
			response.sendRedirect(request.getContextPath() + "/login/toLogin.do");
			return false;
		} else
		{
			// TODO ��¼�ɹ�����¼��¼ʱ��
			return true;
		}
	}
}
