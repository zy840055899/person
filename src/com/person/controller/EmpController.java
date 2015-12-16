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
		// ��ҳ��ѯ��������
		List<Emp> emps = empDao.findAllEmp(empPage);
		// ��ѯ��������������
		int rows = empDao.findAllEmpRows();
		empPage.setRows(rows);
		// ���л�����ݷ���model
		model.addAttribute("emps", emps);
		model.addAttribute("page", empPage);
		// ��ѯ�û��Ƿ��и�Ȩ��
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
		// ����Ա����Ϣ����
		Emp emp = empDao.findEmpById(empId);
		model.addAttribute("emp", emp);
		// ��ɫ�б��ʼ��
		List<Role> roles = roleDao.findAllRolesForShow();
		model.addAttribute("roles", roles);
		// �����б��ʼ��
		List<Dept> depts = deptDao.findAllDeptForShow();
		model.addAttribute("depts", depts);
		return "emp/emp_modify";
	}
	
	@RequestMapping("empModify.do")
	@ResponseBody
	public int empModify(Model model, Emp emp)
	{
		// ����û��޸ĵ������У�ְλ�޳ɾ���Ҫ�ȼ�鵱ǰ�����Ƿ���ھ���
		if("����".equals(emp.getTitle()))
		{
			int deptId = emp.getDeptId();
			Dept dept = deptDao.findDeptById(deptId);
			if(dept.getDeptBossId() > 0)
				return Dict.FAILURE;
		}
		empDao.modifyEmp(emp);
		if("����".equals(emp.getTitle()))
		{
			/*���ò��ž���*/
			// ��þ����ID
			int empId = emp.getEmpId();
			// ��ò��ŵ�ID
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
		// ��ѯ�����н�ɫ���ƣ����ڳ�ʼ����ɫ������
		List<Role> roles = roleDao.findAllRolesForShow();
		model.addAttribute("roles", roles);
		// �����б��ʼ��
		List<Dept> depts = deptDao.findAllDeptForShow();
		model.addAttribute("depts", depts);
		return "emp/emp_add";
	}
	
	/**����ҳ����û�����õ�Ա����������*/
	@RequestMapping("addEmp.do")
	@ResponseBody
	public int addEmp(Emp emp, Model model)
	{
		/*
		 * ���Ա����ְλ�Ǿ����ȼ����ӵ��Ĳ����Ƿ��Ѿ����þ���
		 * ����Ѿ����þ�����ʾ�û�����������
		 * */
		if("����".equals(emp.getTitle()))
		{
			int deptId = emp.getDeptId();
			Dept dept = deptDao.findDeptById(deptId);
			if(dept.getDeptBossId() > 0)
				return Dict.FAILURE;
		}
		/*����Ա��*/
		String birthStr = emp.getIdentityId().substring(6, 14);
		// �����Ա�
		int genderFlag = Integer.parseInt(emp.getIdentityId().charAt(16)+"");
		if(genderFlag % 2 == 0)
		{
			emp.setGender("Ů");
		}else{
			emp.setGender("��");
		}
		Date date = MyDateFormat.dateFormat(birthStr, "yyyyMMdd");
		// ��util.dateת��Ϊsql.date�������ݿ�
		java.sql.Date birthday = new java.sql.Date(date.getTime());
		java.sql.Date enterDate = new java.sql.Date(System.currentTimeMillis());
		// ���ö��������
		emp.setBirthday(birthday);
		// ���ö������ְ����
		emp.setEnterDate(enterDate);
		emp.setUsername(emp.getMobile());
		emp.setPassword(emp.getMobile());
		empDao.addEmp(emp);
		
		if("����".equals(emp.getTitle()))
		{
			/*���ò��ž���*/
			// ��þ����ID
			int empId = emp.getEmpId();
			// ��ò��ŵ�ID
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

