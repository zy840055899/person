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
	
	/**��ѯ�������п�����Ϣ*/
	@RequestMapping("findAllAttend.do")
	public String findAllTrain(Model model, HttpSession session)
	{
		int empId = ((Emp)session.getAttribute("emp")).getEmpId();
		List<Attend> attendsList = attendDao.findAllPersonalAttends(empId);
		model.addAttribute("personalAttends", attendsList);
		// ���û��Ƿ���п��ڹ���Ȩ�޷�װ
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
	
	/**��ת������������Ϣҳ�棨�Ȳ������Ա���Ŀ�����Ϣ��*/
	@RequestMapping("toApproveAttend.do")
	public String toApproveAttend(Model model, HttpSession session)
	{
		List<Attend> attendList = attendDao.findAllAttends();
		model.addAttribute("attends", attendList);
		return "attend/attend_list";
	}
	
	/**��ת����ӿ�����Ϣҳ��*/
	@RequestMapping("toAddAttend.do")
	public String toAddAttend()
	{
		return "attend/attend_add_personal";
	}
	
	/**������ӿ�����Ϣ*/
	@RequestMapping("addAttend.do")
	public String addAttend(Model model, Attend attend, HttpSession session, String dutyDateStr)
	{
		Date date = MyDateFormat.dateFormat(dutyDateStr, "yyyy-MM-dd");
		// ��util.dateת��Ϊsql.date�������ݿ�
		java.sql.Date dutyDate = new java.sql.Date(date.getTime());
		// ����Ա��id
		attend.setEmpId(((Emp)session.getAttribute("emp")).getEmpId());
		// ����Ĭ��û�����ͨ��
		attend.setIsApproved(0);
		// ���ÿ�������
		attend.setDutyDate(dutyDate);
		// ���÷���date 2011-01-01 --> 201101
		attend.setGroupDate(dutyDateStr.substring(0, 4)+dutyDateStr.substring(5, 7));
		attendDao.addAttend(attend);
		return "redirect:findAllAttend.do";
	}
	
	/**��ѯĳ���Ƿ��ѿ���*/
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
	
	/**��˿���*/
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


