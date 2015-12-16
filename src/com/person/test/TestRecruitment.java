package com.person.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.dao.RecruitmentDao;

public class TestRecruitment
{
	@Test
	public void findAllInfo()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(ctx);

		RecruitmentDao recDao =ctx.getBean("recruitmentDao", RecruitmentDao.class);
		
		Map map = new HashMap();
		map.put("id", 10);
		map.put("status", 1);
		
		int a = recDao.changeStatus(map);
		System.out.println(a);
	}
}

