package com.person.entity;

import java.util.List;

/**
 * 部门实体类
 * 2015年3月13日 20:22:47
 */
public class Dept
{
	private int deptId;
	private String deptName;
	private int deptBossId;
	private String deptAddress;

	/**表外关联属性*/
	private List<Emp> empList;				// 本部门所有员工
	private String name;					// 部门经理姓名
	private int empSum;						// 本部门员工个数
	
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
