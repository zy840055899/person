package com.person.controller;

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
import com.person.dao.EmpDao;
import com.person.dao.SalaryDao;
import com.person.entity.AttendMonth;
import com.person.entity.Emp;
import com.person.entity.Salary;
import com.person.util.Dict;
import com.person.util.SalaryCaculator;

@Controller
@RequestMapping("salary")
public class SalaryController
{
	@Resource
	private SalaryDao salDao;
	@Resource
	private EmpDao empDao;
	@Resource
	private AttendDao attendDao;
	
	/**��ת������н����ϸ����ȥ�����н����ϸ��*/
	@RequestMapping("findAllSalary.do")
	public String findAllTrain(Model model, HttpSession session)
	{
		int empId = ((Emp) session.getAttribute("emp")).getEmpId();
		// �����н�ʷ�����Ϣ��list��
		List<Salary> salListPersonal = salDao.findPersonalSalInfo(empId);
		model.addAttribute("personalSalInfo", salListPersonal);
		// ���û��Ƿ����н�ʹ���Ȩ�޷�װ
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.SALARY)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "salary/salary_list_personal";
	}
	
	/**��ת����������ϸ����ȥ������Ա����н�ʷַ���*/
	@RequestMapping("toAddSal.do")
	public String toAddSal(Model model)
	{
		List<Salary> salInfoList = salDao.findAllSalInfo();
		model.addAttribute("allSalInfo", salInfoList);
		return "salary/salary_list";
	}
	
	/**�����ʣ��Ȳ�ѯ�����Ƿ񷢹����ʣ�
	 * @throws Exception */
	@RequestMapping("addSalary.do")
	@ResponseBody
	public int addSalary(String monthFor, int workdayCount) throws Exception
	{
		// ������ڸ�ʽ��20145ת����201405
		if(monthFor.length() == 5)
		{
			StringBuffer sb = new StringBuffer();
			sb.append(monthFor.substring(0, 4)).append("0").append(monthFor.charAt(4));
			monthFor = sb.toString();
		}
		int a = salDao.qryIfExistSal(monthFor);
		if(a > 0)
			return Dict.FAILURE;
		
		// ��ʽ������
		// �ؼ�����һ���������Ա����id��������н�ʵȼ���
		List<Emp> empList = empDao.qryAllEmpForSal();
		// �������list������ÿ��Ա���ź��·�ȥ��ѯ������Ϣ
		for(Emp emp : empList)
		{
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("empId", emp.getEmpId());
			paramMap.put("groupDate", monthFor);
			// �ؼ�������������Ա����Ŀ���·ݵĿ��ڻ�����Ϣ
			AttendMonth am = attendDao.findAttendMonth(paramMap);
			// �½�һ��н����Ϣ�����ݻ�����Ϣ��װ��������
			Salary sal = new Salary();
			sal.setEmpId(emp.getEmpId());
			sal.setMonthFor(monthFor);
			// �ؼ����������������ó��������������н�ʣ��Ӱಹ������������ٸ��ݷ��������н�ʣ����
			// ��Ϊ�վ�ȥ��
			if(am != null)
			{
				// ����н�ʣ����ݸ��¹����պ�Ա���Ŀ����������
				int basicSal = SalaryCaculator.calculatrBasicSal(emp.getSalGrade(), workdayCount, am.getSumWorkTime());
				sal.setBasicSal(basicSal);
				// �Ӱ�н��
				sal.setExtraSal(am.getSumExtraTime() * Dict.Salary.SAL_EXTRA);
				// �����
				sal.setTravelSal(am.getSumTravel() * Dict.Salary.SAL_TRAVEL);
			}
			// ��Ϊ�գ���ֻ��װempid��monthfor������ʵ����Ĭ��Ϊ0�����账��
			// �����������
			salDao.addSalaryForEmp(sal);
		}
		return Dict.SUCCESS;
	}
}
