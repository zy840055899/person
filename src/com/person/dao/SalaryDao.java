package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Salary;

@MyRepository
public interface SalaryDao
{
	/**��ѯĳ���Ƿ񷢹�����*/
	int qryIfExistSal(String dateStr);
	/**����ĳԱ��ĳ��н����Ϣ*/
	int addSalaryForEmp(Salary sal);
	/**��ѯ��������н�ʷ�������*/
	List<Salary> findPersonalSalInfo(int empId);
	/**��ѯ������н�ʷַ�����*/
	List<Salary> findAllSalInfo();
}
