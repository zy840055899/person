package com.person.entity;

import java.util.List;

/**
 * ����ʵ����
 * 2015��3��13�� 20:22:47
 */
public class Dept
{
	private int deptId;
	private String deptName;
	private int deptBossId;
	private String deptAddress;

	/**�����������*/
	private List<Emp> empList;				// ����������Ա��
	private String name;					// ���ž�������
	private int empSum;						// ������Ա������
	
	public int getEmpSum()
	{
		return empSum;
	}
	public void setEmpSum(int empSum)
	{
		this.empSum = empSum;
	}
	public int getDeptId()
	{
		return deptId;
	}
	public void setDeptId(int deptId)
	{
		this.deptId = deptId;
	}
	public String getDeptName()
	{
		return deptName;
	}
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	public int getDeptBossId()
	{
		return deptBossId;
	}
	public void setDeptBossId(int deptBossId)
	{
		this.deptBossId = deptBossId;
	}
	public String getDeptAddress()
	{
		return deptAddress;
	}
	public void setDeptAddress(String deptAddress)
	{
		this.deptAddress = deptAddress;
	}
	public List<Emp> getEmpList()
	{
		return empList;
	}
	public void setEmpList(List<Emp> empList)
	{
		this.empList = empList;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", deptBossId=" + deptBossId + ", deptAddress=" + deptAddress + ", empList=" + empList + ", name=" + name + ", empSum=" + empSum
				+ "]";
	}
}
