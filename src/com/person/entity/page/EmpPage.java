package com.person.entity.page;

/**
 * ��װһЩ��������
 * @author ���˶� 2015��5��30�� 11:31:51
 */
public class EmpPage extends Page
{
	private String name;
	private String gender;
	private String dept;
	public String getDept()
	{
		return dept;
	}
	public void setDept(String dept)
	{
		this.dept = dept.trim();
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name.trim();
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender.trim();
	}
	@Override
	public String toString()
	{
		return "EmpPage [name=" + name + ", gender=" + gender + ", dept=" + dept + "]";
	}
	
}
