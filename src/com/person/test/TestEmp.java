package com.person.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.person.dao.DeptDao;
import com.person.dao.EmpDao;
import com.person.entity.Emp;

public class TestEmp
{
	@Test
	public void findAllInfo()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(ctx);

		EmpDao empDao =ctx.getBean("empDao", EmpDao.class);
		DeptDao deptDao =ctx.getBean("deptDao", DeptDao.class);
		
		// ����ȫ��Ա��
//		List<Emp> a  = dao.findAllEmp();
//		Gson gson = new Gson();
//		String str = gson.toJson(a, List.class);
//		System.out.println(str);
//		
		// ɾ��Ա��
//		int flag = dao.delEmpById(8);
//		System.out.println(flag);
		
		//���ҵ���Ա��
//		Emp e = dao.findEmpById(1);
//		System.out.println(e);
		// ��ѯ���в�������
//		List<Map> list = deptDao.findAllDeptName();
//		System.out.println(list);
		
		Emp emp = new Emp();
		emp.setEmpId(1);
		emp.setName("����");
		emp.setMobile("13912162145");
		emp.setEmail("915152236@qq.com");
		emp.setWorkPhone("01012341234");
		int a = empDao.modifyEmp(emp);
		System.out.println(a);
	}
}

