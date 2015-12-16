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
	
	/**跳转到个人薪资明细表（先去查个人薪资明细表）*/
	@RequestMapping("findAllSalary.do")
	public String findAllTrain(Model model, HttpSession session)
	{
		int empId = ((Emp) session.getAttribute("emp")).getEmpId();
		// 查个人薪资发放信息（list）
		List<Salary> salListPersonal = salDao.findPersonalSalInfo(empId);
		model.addAttribute("personalSalInfo", salListPersonal);
		// 将用户是否具有薪资管理权限封装
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
	
	/**跳转到发工资明细表（先去查所有员工的薪资分发表）*/
	@RequestMapping("toAddSal.do")
	public String toAddSal(Model model)
	{
		List<Salary> salInfoList = salDao.findAllSalInfo();
		model.addAttribute("allSalInfo", salInfoList);
		return "salary/salary_list";
	}
	
	/**发工资（先查询该月是否发过工资）
	 * @throws Exception */
	@RequestMapping("addSalary.do")
	@ResponseBody
	public int addSalary(String monthFor, int workdayCount) throws Exception
	{
		// 如果日期格式是20145转化成201405
		if(monthFor.length() == 5)
		{
			StringBuffer sb = new StringBuffer();
			sb.append(monthFor.substring(0, 4)).append("0").append(monthFor.charAt(4));
			monthFor = sb.toString();
		}
		int a = salDao.qryIfExistSal(monthFor);
		if(a > 0)
			return Dict.FAILURE;
		
		// 正式发工资
		// 关键步骤一：查出所有员工（id，姓名，薪资等级）
		List<Emp> empList = empDao.qryAllEmpForSal();
		// 遍历这个list，根据每个员工号和月份去查询考勤信息
		for(Emp emp : empList)
		{
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("empId", emp.getEmpId());
			paramMap.put("groupDate", monthFor);
			// 关键步骤二：查出该员工的目标月份的考勤汇总信息
			AttendMonth am = attendDao.findAttendMonth(paramMap);
			// 新建一条薪资信息，根据汇总信息封装各个数据
			Salary sal = new Salary();
			sal.setEmpId(emp.getEmpId());
			sal.setMonthFor(monthFor);
			// 关键步骤三：根据所得出的数据求出基本薪资，加班补贴，出差补贴，再根据罚款合算月薪资，入库
			// 不为空就去算
			if(am != null)
			{
				// 基本薪资：根据该月工作日和员工的考勤天数获得
				int basicSal = SalaryCaculator.calculatrBasicSal(emp.getSalGrade(), workdayCount, am.getSumWorkTime());
				sal.setBasicSal(basicSal);
				// 加班薪资
				sal.setExtraSal(am.getSumExtraTime() * Dict.Salary.SAL_EXTRA);
				// 出差补助
				sal.setTravelSal(am.getSumTravel() * Dict.Salary.SAL_TRAVEL);
			}
			// 若为空，则只封装empid和monthfor，其他实体类默认为0，不予处理
			// 插入该条数据
			salDao.addSalaryForEmp(sal);
		}
		return Dict.SUCCESS;
	}
}
