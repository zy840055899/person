package com.person.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.dao.AttendDao;

public class TestAttend
{
	@Test
	public void findAllInfo()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(ctx);

		AttendDao attendDao =ctx.getBean("attendDao", AttendDao.class);
		
		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("empId", emp.getEmpId());
//		paramMap.put("dutyDate", dutyDateStr);
		
		
	}
}

