package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Attend;
import com.person.entity.AttendMonth;

@MyRepository
public interface AttendDao
{
	/**查询所有员工考勤信息*/
	List<Attend> findAllAttends();
	/**员工查询个人考勤信息*/
	List<Attend> findAllPersonalAttends(int empId);
	/**查询员工目标月数考勤信息汇总*/
	AttendMonth findAttendMonth(Map<String, Object> map);
	/**员工添加个人考勤信息*/
	int addAttend(Attend attend);
	/**查询某日员工是否已经考勤*/
	int queryIfAttend(Map<String, Object> map);
	/**审批考勤信息*/
	int checkAttend(Map<String, Object> map);
}
