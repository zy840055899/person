package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Dept;
import com.person.entity.page.DeptPage;

@MyRepository
public interface DeptDao
{
	/**查询全部部门信息*/
	List<Dept> findAllDept(DeptPage deptPage);
	List<Dept> findAllDeptForShow();
	/**查出所有数据的条数，用来分页*/
	int findAllDeptRows();
	/**按照部门号删除部门*/
	int delDeptById(int deptId);
	/**增加部门*/
	int addDept(Dept dept);
	/**按照部门号查询部门*/
	Dept findDeptById(int deptId);
	/**修改部门*/
	int modifyDept(Dept dept);
	/**设置部门经理*/
	int setDeptBoss(Map<String, Integer> param);
}
