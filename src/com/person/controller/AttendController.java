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

import com.person.dao.AttendDao;
import com.person.entity.Attend;
import com.person.entity.Emp;
import com.person.util.Dict;
import com.person.util.MyDateFormat;

@Controller
@RequestMapping("attend")
public class AttendController
{
	@Resource
	AttendDao attendDao;
	
	/**查询个人所有考勤信息*/
	@RequestMapping("findAllAttend.do")
	public String findAllTrain(Model model, HttpSession session)
	{
		int empId = ((Emp)session.getAttribute("emp")).getEmpId();
		List<Attend> attendsList = attendDao.findAllPersonalAttends(empId);
		model.addAttribute("personalAttends", attendsList);
		// 将用户是否具有考勤管理权限封装
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.ATTEND)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "attend/attend_list_personal";
	}
	
	/**跳转到审批考勤信息页面（先查出所有员工的考勤信息）*/
	@RequestMapping("toApproveAttend.do")
	public String toApproveAttend(Model model, HttpSession session)
	{
		List<Attend> attendList = attendDao.findAllAttends();
		model.addAttribute("attends", attendList);
		return "attend/attend_list";
	}
	
	/**跳转到添加考勤信息页面*/
	@RequestMapping("toAddAttend.do")
	public String toAddAttend()
	{
		return "attend/attend_add_personal";
	}
	
	/**个人添加考勤信息*/
	@RequestMapping("addAttend.do")
	public String addAttend(Model model, Attend attend, HttpSession session, String dutyDateStr)
	{
		Date date = MyDateFormat.dateFormat(dutyDateStr, "yyyy-MM-dd");
		// 将util.date转变为sql.date存入数据库
		java.sql.Date dutyDate = new java.sql.Date(date.getTime());
		// 设置员工id
		attend.setEmpId(((Emp)session.getAttribute("emp")).getEmpId());
		// 设置默认没有审核通过
		attend.setIsApproved(0);
		// 设置考勤日期
		attend.setDutyDate(dutyDate);
		// 设置分组date 2011-01-01 --> 201101
		attend.setGroupDate(dutyDateStr.substring(0, 4)+dutyDateStr.substring(5, 7));
		attendDao.addAttend(attend);
		return "redirect:findAllAttend.do";
	}
	
	/**查询某日是否已考勤*/
	@RequestMapping("queryIfAttend.do")
	@ResponseBody
	public int queryIfAttend(Model model, String dutyDateStr, HttpSession session)
	{
		Emp emp = (Emp) session.getAttribute("emp");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("empId", emp.getEmpId());
		paramMap.put("dutyDate", dutyDateStr);
		int a = attendDao.queryIfAttend(paramMap);
		if(a == 0)
			return Dict.SUCCESS;
		return Dict.FAILURE;
	}
	
	/**审核考勤*/
	@RequestMapping("checkAttend.do")
	public String checkAttend(int attendId, int status)
	{
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attendId", attendId);
		paramMap.put("status", status);
		attendDao.checkAttend(paramMap);
		return "redirect:toApproveAttend.do";
	}
	
	
}


