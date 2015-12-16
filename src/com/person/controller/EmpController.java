package com.person.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.person.dao.DeptDao;
import com.person.dao.EmpDao;
import com.person.dao.RoleDao;
import com.person.entity.Dept;
import com.person.entity.Emp;
import com.person.entity.Role;
import com.person.entity.page.EmpPage;
import com.person.util.Dict;
import com.person.util.MyDateFormat;

@Controller
@RequestMapping("emp")
@SessionAttributes("empPage")
public class EmpController
{
	@Resource
	private EmpDao empDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private DeptDao deptDao;
	
	@RequestMapping("findAllEmp.do")
	public String findAllEmp(EmpPage empPage, Model model, HttpSession session)
	{
		// 按页查询所有数据
		List<Emp> emps = empDao.findAllEmp(empPage);
		// 查询出共多少条数据
		int rows = empDao.findAllEmpRows();
		empPage.setRows(rows);
		// 所有获得数据放入model
		model.addAttribute("emps", emps);
		model.addAttribute("page", empPage);
		// 查询用户是否有该权限
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.EMPLOYEE)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		empPage.setName("");
		empPage.setGender("");
		empPage.setDept("");
		return "emp/emp_list";
	}
	
	@RequestMapping("delEmpById.do")
	@ResponseBody
	public int delEmpById(int empId, HttpSession session)
	{
		if(empId == ((Emp)session.getAttribute("emp")).getEmpId())
		{
			return Dict.FAILURE;
		}
		empDao.delEmpById(empId);
//		return "redirect: findAllEmp.do";
		return Dict.SUCCESS;
	}
	
	@RequestMapping("toEmpModify.do")
	public String toEmpModify(int empId, Model model)
	{
		// 用于员工信息回显
		Emp emp = empDao.findEmpById(empId);
		model.addAttribute("emp", emp);
		// 角色列表初始化
		List<Role> roles = roleDao.findAllRolesForShow();
		model.addAttribute("roles", roles);
		// 部门列表初始化
		List<Dept> depts = deptDao.findAllDeptForShow();
		model.addAttribute("depts", depts);
		return "emp/emp_modify";
	}
	
	@RequestMapping("empModify.do")
	@ResponseBody
	public int empModify(Model model, Emp emp)
	{
		// 如果用户修改的内容中，职位修成经理，要先检查当前部门是否存在经理
		if("经理".equals(emp.getTitle()))
		{
			int deptId = emp.getDeptId();
			Dept dept = deptDao.findDeptById(deptId);
			if(dept.getDeptBossId() > 0)
				return Dict.FAILURE;
		}
		empDao.modifyEmp(emp);
		if("经理".equals(emp.getTitle()))
		{
			/*设置部门经理*/
			// 获得经理的ID
			int empId = emp.getEmpId();
			// 获得部门的ID
			int deptId = emp.getDeptId();
			Map<String, Integer> param = new HashMap<>();
			param.put("deptId", deptId);
			param.put("empId", empId);
			deptDao.setDeptBoss(param);
		}
		return Dict.SUCCESS;
	}
	
	@RequestMapping("toAddEmp.do")
	public String toAddEmp(Model model)
	{
		// 查询出所有角色名称，用于初始化角色下拉框
		List<Role> roles = roleDao.findAllRolesForShow();
		model.addAttribute("roles", roles);
		// 部门列表初始化
		List<Dept> depts = deptDao.findAllDeptForShow();
		model.addAttribute("depts", depts);
		return "emp/emp_add";
	}
	
	/**设置页面上没有设置的员工必有属性*/
	@RequestMapping("addEmp.do")
	@ResponseBody
	public int addEmp(Emp emp, Model model)
	{
		/*
		 * 如果员工的职位是经理，先检查添加到的部门是否已经设置经理
		 * 如果已经设置经理，提示用户的输入有误
		 * */
		if("经理".equals(emp.getTitle()))
		{
			int deptId = emp.getDeptId();
			Dept dept = deptDao.findDeptById(deptId);
			if(dept.getDeptBossId() > 0)
				return Dict.FAILURE;
		}
		/*插入员工*/
		String birthStr = emp.getIdentityId().substring(6, 14);
		// 设置性别
		int genderFlag = Integer.parseInt(emp.getIdentityId().charAt(16)+"");
		if(genderFlag % 2 == 0)
		{
			emp.setGender("女");
		}else{
			emp.setGender("男");
		}
		Date date = MyDateFormat.dateFormat(birthStr, "yyyyMMdd");
		// 将util.date转变为sql.date存入数据库
		java.sql.Date birthday = new java.sql.Date(date.getTime());
		java.sql.Date enterDate = new java.sql.Date(System.currentTimeMillis());
		// 设置对象的生日
		emp.setBirthday(birthday);
		// 设置对象的入职日期
		emp.setEnterDate(enterDate);
		emp.setUsername(emp.getMobile());
		emp.setPassword(emp.getMobile());
		empDao.addEmp(emp);
		
		if("经理".equals(emp.getTitle()))
		{
			/*设置部门经理*/
			// 获得经理的ID
			int empId = emp.getEmpId();
			// 获得部门的ID
			int deptId = emp.getDeptId();
			Map<String, Integer> param = new HashMap<>();
			param.put("deptId", deptId);
			param.put("empId", empId);
			deptDao.setDeptBoss(param);
		}
		return Dict.SUCCESS;
	}
	
	@RequestMapping("checkMobile.do")
	@ResponseBody
	public int checkMobile(String mobile, int empId)
	{
		Emp emp = empDao.findEmpByUsername(mobile);
		if(emp == null || emp.getEmpId() == empId)
		{
			return Dict.SUCCESS;
		}else{
			return Dict.FAILURE;
		}
	}
}

