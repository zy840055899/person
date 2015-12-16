package com.person.dao;

import java.util.List;
import java.util.Map;

import com.person.annotation.MyRepository;
import com.person.entity.Attend;
import com.person.entity.AttendMonth;

@MyRepository
public interface AttendDao
{
	/**��ѯ����Ա��������Ϣ*/
	List<Attend> findAllAttends();
	/**Ա����ѯ���˿�����Ϣ*/
	List<Attend> findAllPersonalAttends(int empId);
	/**��ѯԱ��Ŀ������������Ϣ����*/
	AttendMonth findAttendMonth(Map<String, Object> map);
	/**Ա����Ӹ��˿�����Ϣ*/
	int addAttend(Attend attend);
	/**��ѯĳ��Ա���Ƿ��Ѿ�����*/
	int queryIfAttend(Map<String, Object> map);
	/**����������Ϣ*/
	int checkAttend(Map<String, Object> map);
}
