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
	
	/**�������в�����Ϣ*/
	@RequestMapping("findAllDept.do")
	public String findAllDept(DeptPage deptPage, Model model, HttpSession session)
	{
		// ��ҳ��ѯ���в���
		List<Dept> depts = deptDao.findAllDept(deptPage);
		// ���һ������������
		int rows = deptDao.findAllDeptRows();
		deptPage.setRows(rows);
		// ������ݷ���model
		model.addAttribute("depts", depts);
		model.addAttribute("page", deptPage);
		
		// ��ѯ�û��Ƿ��и�Ȩ��
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
	
	/**ɾ������*/
	@RequestMapping("delDeptById.do")
	@ResponseBody
	public int delDeptById(int deptId, HttpSession session)
	{
		// �ȼ��ɾ���Ĳ����Ƿ��Լ����ڵĲ���
		Emp temp = (Emp) session.getAttribute("emp");
		Emp emp = empDao.findEmpById(temp.getEmpId());
		if(emp.getDeptId() == deptId)
		{
			return Dict.FAILURE;
		}
		deptDao.delDeptById(deptId);
		return Dict.SUCCESS;
	}
	
	/**ת�����Ӳ���*/
	@RequestMapping("toAddDept.do")
	public String toAddDept()
	{
		return "dept/dept_add";
	}
	
	/**���Ӳ���*/
	@RequestMapping("addDept.do")
	public String addDept(Dept dept)
	{
		deptDao.addDept(dept);
		return "redirect:findAllDept.do";
	}
	
	/**ת���޸Ĳ���*/
	@RequestMapping("toModifyDept.do")
	public String toModifyDept(int deptId, Model model)
	{
		Dept dept = deptDao.findDeptById(deptId);
		model.addAttribute("dept", dept);
		return "dept/dept_modify";
	}
	
	/**�޸Ĳ���*/
	@RequestMapping("modifyDept.do")
	public String modifyDept(Dept dept)
	{
		deptDao.modifyDept(dept);
		return "redirect: findAllDept.do";
	}
	
}
