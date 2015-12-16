package com.person.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.dao.RoleDao;
import com.person.entity.Module;
import com.person.entity.Role;

public class TestRole
{
	@Test
	public void findAllInfo()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(ctx);

		RoleDao roleDao =ctx.getBean("roleDao", RoleDao.class);
		
		Role a = roleDao.findRoleByName("æ≠¿Ì");
		
		System.out.println(a);
		
	}
}

