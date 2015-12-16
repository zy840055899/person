package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Dept;
import com.person.entity.page.DeptPage;

@MyRepository
public interface DeptDao
{
	/**��ѯȫ��������Ϣ*/
	List<Dept> findAllDept(DeptPage deptPage);
	List<Dept> findAllDeptForShow();
	/**����������ݵ�������������ҳ*/
	int findAllDeptRows();
	/**���ղ��ź�ɾ������*/
	int delDeptById(int deptId);
	/**���Ӳ���*/
	int addDept(Dept dept);
	/**���ղ��źŲ�ѯ����*/
	Dept findDeptById(int deptId);
	/**�޸Ĳ���*/
	int modifyDept(Dept dept);
	/**���ò��ž���*/
	int setDeptBoss(Map<String, Integer> param);
}
