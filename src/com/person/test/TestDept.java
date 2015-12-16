package com.person.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.dao.DeptDao;

public class TestDept
{
	@Test
	public void findAllInfo()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(ctx);

		DeptDao deptDao =ctx.getBean("deptDao", DeptDao.class);
		
		Map<String, Integer> param = new HashMap<>();
		param.put("deptId", 10);
		param.put("empId", 3);
		deptDao.setDeptBoss(param);
		
		
	}
}

