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

/**个人管理相关功能*/
@Controller
@RequestMapping("user")
public class UserController
{
	@Resource
	private EmpDao empDao;
	
	/**打开修改个人信息页面*/
	@RequestMapping("toModifyUserInfo.do")
	public String toModifyUserInfo(HttpSession session, Model model)
	{
		Emp temp = (Emp) session.getAttribute("emp");	// 获取用户登录信息
		Emp emp = empDao.findEmpById(temp.getEmpId());	// 获取用户详细信息
		model.addAttribute("emp", emp);
		return "user/userInfo_modify";
	}
	
	/**修改个人信息*/
	@RequestMapping("modifyUserInfo.do")
	@ResponseBody
	public int modifyUserInfo(Emp emp)
	{
		// 判断员工修改的手机号码和其他员工是否冲突
		Emp temp = empDao.findEmpByUsername(emp.getMobile());
		// 如果根据新填写的手机号找不到员工，或者只找到了自己，可以使用新的手机号码
		if(temp == null || temp.getEmpId() == emp.getEmpId())
		{
			empDao.modifyUserInfo(emp);
			return Dict.SUCCESS;
		}
		return Dict.USERNAME_ERROR;
	}

	/**打开修改密码页*/
	@RequestMapping("toModifyPwd.do")
	public String toModifyPwd()
	{
		return "user/userPwd_modify";
	}
	
	/**修改密码*/
	@RequestMapping("modifyPwd.do")
	@ResponseBody
	public int modifyPwd(String password, String newPassword, HttpSession session)
	{
		Emp emp = (Emp) session.getAttribute("emp");
		if(password.equals(emp.getPassword()))
		{
			emp.setPassword(newPassword);	// 设置新密码
			empDao.modifyPwd(emp);			// 执行修改密码
			return Dict.SUCCESS;
		}
		return Dict.PASSWORD_ERROR;
	}
}
