package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Emp;
import com.person.entity.page.EmpPage;

@MyRepository
public interface EmpDao
{
	/**��ѯȫ��Ա����Ϣ*/
	List<Emp> findAllEmp(EmpPage empPage);
	/**��ѯ����������*/
	int findAllEmpRows();
	/**����ID����Emp��Ϣ*/
	Emp findEmpById(int empId);
	/**�����û�������Emp��Ϣ*/
	Emp findEmpByUsername(String mobile);
	/**����Ա����ɾ��Ա��*/
	int delEmpById(int empId);
	/**�޸�Ա����Ϣ*/
	int modifyEmp(Emp emp);
	/**�޸�Ա����Ϣ*/
	int modifyUserInfo(Emp emp);
	/**���Ա��*/
	int addEmp(Emp emp);
	/**�޸�Ա������*/
	int modifyPwd(Emp emp);
	/**��ѯ����Ա��н�ʵȼ���Ϣ�������ʣ�*/
	List<Emp> qryAllEmpForSal();
}
