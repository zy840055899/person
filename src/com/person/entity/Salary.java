package com.person.entity;

public class Salary
{
	private int salId;			// 薪资信息ID
	private int empId;			// 员工ID
	private String monthFor;	// 发放的月份
	private int basicSal=0;		// 基本工资（根据员工的基本薪资等级和当月的工作时间/总的应该工作时间得出）
	private int extraSal=0;		// 加班补助
	private int travelSal=0;	// 出差补助
	private int forfeit=0;		// 罚金（例如迟到等）
	// 表外属性
	private int sumSal;			// 当月的薪资汇总
	private String name;		// 员工姓名
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSalId()
	{
		return salId;
	}
	public void setSalId(int salId)
	{
		this.salId = salId;
	}
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public String getMonthFor()
	{
		return monthFor;
	}
	public void setMonthFor(String monthFor)
	{
		this.monthFor = monthFor;
	}
	public int getBasicSal()
	{
		return basicSal;
	}
	public void setBasicSal(int basicSal)
	{
		this.basicSal = basicSal;
	}
	public int getExtraSal()
	{
		return extraSal;
	}
	public void setExtraSal(int extraSal)
	{
		this.extraSal = extraSal;
	}
	public int getTravelSal()
	{
		return travelSal;
	}
	public void setTravelSal(int travelSal)
	{
		this.travelSal = travelSal;
	}
	public int getForfeit()
	{
		return forfeit;
	}
	public void setForfeit(int forfeit)
	{
		this.forfeit = forfeit;
	}
	// 薪资总和 = 基本薪资+出差补助+加班补助-罚金；
	public int getSumSal()
	{
		return basicSal+travelSal+extraSal-forfeit;
	}
	public void setSumSal(int sumSal)
	{
		this.sumSal = sumSal;
	}
	@Override
	public String toString()
	{
		return "Salary [salId=" + salId + ", empId=" + empId + ", monthFor=" + monthFor + ", basicSal=" + basicSal + ", extraSal=" + extraSal + ", travelSal=" + travelSal + ", forfeit=" + forfeit
				+ ", sumSal=" + sumSal + ", name=" + name + "]";
	}
}
