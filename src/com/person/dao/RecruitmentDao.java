package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Recruitment;
import com.person.entity.page.RecruitmentPage;

@MyRepository
public interface RecruitmentDao
{
	/**查询全部招聘*/
	List<Recruitment> findAllRec(RecruitmentPage recruitmentPage);
	/**查询所有招聘的条数*/
	int findAllRecRows();
	/**按照招聘ID查询招聘*/
	Recruitment findRecById(int recId);
	/**按照招聘ID删除招聘*/
	int delRecById(int recId);
	/**添加招聘*/
	int addRec(Recruitment rec);
	/**修改招聘*/
	int modifyRec(Recruitment rec);
	/**修改招聘状态*/
	int changeStatus(Map<String, Integer> map);
}
