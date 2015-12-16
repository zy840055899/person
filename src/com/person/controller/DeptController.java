package com.person.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.person.dao.DeptDao;
import com.person.dao.EmpDao;
import com.person.entity.Dept;
import com.person.entity.Emp;
import com.person.entity.page.DeptPage;
import com.person.util.Dict;

@Controller
@RequestMapping("dept")
@SessionAttributes("deptPage")
public class DeptController
{
	@Resource
	private DeptDao deptDao;
	@Resource
	private EmpDao empDao;
	
	/**查找所有部门信息*/
	@RequestMapping("findAllDept.do")
	public String findAllDept(DeptPage deptPage, Model model, HttpSession session)
	{
		// 按页查询所有部门
		List<Dept> depts = deptDao.findAllDept(deptPage);
		// 查出一共多少条数据
		int rows = deptDao.findAllDeptRows();
		deptPage.setRows(rows);
		// 获得数据放入model
		model.addAttribute("depts", depts);
		model.addAttribute("page", deptPage);
		
		// 查询用户是否有该权限
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.DEPT)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "dept/dept_list";
	}
	
	/**删除部门*/
	@RequestMapping("delDeptById.do")
	@ResponseBody
	public int delDeptById(int deptId, HttpSession session)
	{
		// 先检查删除的部门是否自己所在的部门
		Emp temp = (Emp) session.getAttribute("emp");
		Emp emp = empDao.findEmpById(temp.getEmpId());
		if(emp.getDeptId() == deptId)
		{
			return Dict.FAILURE;
		}
		deptDao.delDeptById(deptId);
		return Dict.SUCCESS;
	}
	
	/**转到增加部门*/
	@RequestMapping("toAddDept.do")
	public String toAddDept()
	{
		return "dept/dept_add";
	}
	
	/**增加部门*/
	@RequestMapping("addDept.do")
	public String addDept(Dept dept)
	{
		deptDao.addDept(dept);
		return "redirect:findAllDept.do";
	}
	
	/**转到修改部门*/
	@RequestMapping("toModifyDept.do")
	public String toModifyDept(int deptId, Model model)
	{
		Dept dept = deptDao.findDeptById(deptId);
		model.addAttribute("dept", dept);
		return "dept/dept_modify";
	}
	
	/**修改部门*/
	@RequestMapping("modifyDept.do")
	public String modifyDept(Dept dept)
	{
		deptDao.modifyDept(dept);
		return "redirect: findAllDept.do";
	}
	
}
