package com.person.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.person.dao.EmpDao;
import com.person.entity.Emp;
import com.person.util.Dict;

/**���˹�����ع���*/
@Controller
@RequestMapping("user")
public class UserController
{
	@Resource
	private EmpDao empDao;
	
	/**���޸ĸ�����Ϣҳ��*/
	@RequestMapping("toModifyUserInfo.do")
	public String toModifyUserInfo(HttpSession session, Model model)
	{
		Emp temp = (Emp) session.getAttribute("emp");	// ��ȡ�û���¼��Ϣ
		Emp emp = empDao.findEmpById(temp.getEmpId());	// ��ȡ�û���ϸ��Ϣ
		model.addAttribute("emp", emp);
		return "user/userInfo_modify";
	}
	
	/**�޸ĸ�����Ϣ*/
	@RequestMapping("modifyUserInfo.do")
	@ResponseBody
	public int modifyUserInfo(Emp emp)
	{
		// �ж�Ա���޸ĵ��ֻ����������Ա���Ƿ��ͻ
		Emp temp = empDao.findEmpByUsername(emp.getMobile());
		// �����������д���ֻ����Ҳ���Ա��������ֻ�ҵ����Լ�������ʹ���µ��ֻ�����
		if(temp == null || temp.getEmpId() == emp.getEmpId())
		{
			empDao.modifyUserInfo(emp);
			return Dict.SUCCESS;
		}
		return Dict.USERNAME_ERROR;
	}

	/**���޸�����ҳ*/
	@RequestMapping("toModifyPwd.do")
	public String toModifyPwd()
	{
		return "user/userPwd_modify";
	}
	
	/**�޸�����*/
	@RequestMapping("modifyPwd.do")
	@ResponseBody
	public int modifyPwd(String password, String newPassword, HttpSession session)
	{
		Emp emp = (Emp) session.getAttribute("emp");
		if(password.equals(emp.getPassword()))
		{
			emp.setPassword(newPassword);	// ����������
			empDao.modifyPwd(emp);			// ִ���޸�����
			return Dict.SUCCESS;
		}
		return Dict.PASSWORD_ERROR;
	}
}
