package com.person.entity;

/**
 * ÿ���ܿ��ڻ��ܣ����ڽ��㹤�ʣ�
 */
public class AttendMonth
{
	private int empId;			
	private int sumWorkTime = 0;		// �ܵĹ���ʱ��
	private int sumExtraTime = 0;		// �ܵļӰ�ʱ��
	private int sumTravel = 0;			// �ܵĳ�������
	private String groupDate;			// ���ڻ��ܵ�����
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
