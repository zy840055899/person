package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Recruitment;
import com.person.entity.page.RecruitmentPage;

@MyRepository
public interface RecruitmentDao
{
	/**��ѯȫ����Ƹ*/
	List<Recruitment> findAllRec(RecruitmentPage recruitmentPage);
	/**��ѯ������Ƹ������*/
	int findAllRecRows();
	/**������ƸID��ѯ��Ƹ*/
	Recruitment findRecById(int recId);
	/**������ƸIDɾ����Ƹ*/
	int delRecById(int recId);
	/**�����Ƹ*/
	int addRec(Recruitment rec);
	/**�޸���Ƹ*/
	int modifyRec(Recruitment rec);
	/**�޸���Ƹ״̬*/
	int changeStatus(Map<String, Integer> map);
}
