package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Emp;
import com.person.entity.page.EmpPage;

@MyRepository
public interface EmpDao
{
	/**查询全部员工信息*/
	List<Emp> findAllEmp(EmpPage empPage);
	/**查询数据总条数*/
	int findAllEmpRows();
	/**根据ID查找Emp信息*/
	Emp findEmpById(int empId);
	/**根据用户名查找Emp信息*/
	Emp findEmpByUsername(String mobile);
	/**按照员工号删除员工*/
	int delEmpById(int empId);
	/**修改员工信息*/
	int modifyEmp(Emp emp);
	/**修改员工信息*/
	int modifyUserInfo(Emp emp);
	/**添加员工*/
	int addEmp(Emp emp);
	/**修改员工密码*/
	int modifyPwd(Emp emp);
	/**查询所有员工薪资等级信息（发工资）*/
	List<Emp> qryAllEmpForSal();
}
