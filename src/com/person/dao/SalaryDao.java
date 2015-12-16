package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Salary;

@MyRepository
public interface SalaryDao
{
	/**查询某月是否发过工资*/
	int qryIfExistSal(String dateStr);
	/**插入某员工某月薪资信息*/
	int addSalaryForEmp(Salary sal);
	/**查询个人所有薪资发放详情*/
	List<Salary> findPersonalSalInfo(int empId);
	/**查询所有人薪资分发详情*/
	List<Salary> findAllSalInfo();
}
