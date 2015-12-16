package com.person.entity;

/**
 * 每月总考勤汇总（用于结算工资）
 */
public class AttendMonth
{
	private int empId;			
	private int sumWorkTime = 0;		// 总的工作时间
	private int sumExtraTime = 0;		// 总的加班时间
	private int sumTravel = 0;			// 总的出差天数
	private String groupDate;			// 考勤汇总的月数
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public int getSumWorkTime()
	{
		return sumWorkTime;
	}
	public void setSumWorkTime(int sumWorkTime)
	{
		this.sumWorkTime = sumWorkTime;
	}
	public int getSumExtraTime()
	{
		return sumExtraTime;
	}
	public void setSumExtraTime(int sumExtraTime)
	{
		this.sumExtraTime = sumExtraTime;
	}
	public int getSumTravel()
	{
		return sumTravel;
	}
	public void setSumTravel(int sumTravel)
	{
		this.sumTravel = sumTravel;
	}
	public String getGroupDate()
	{
		return groupDate;
	}
	public void setGroupDate(String groupDate)
	{
		this.groupDate = groupDate;
	}
	@Override
	public String toString()
	{
		return "AttendMonth [empId=" + empId + ", sumWorkTime=" + sumWorkTime + ", sumExtraTime=" + sumExtraTime + ", sumTravel=" + sumTravel + ", groupDate=" + groupDate + "]";
	}
}
