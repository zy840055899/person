package com.person.entity;

import java.sql.Date;

/**
 * 培训实体类
 * 2015年3月15日 17:22:31
 */
public class Train
{
	private int trainId;
	private String tname;
	private Date startDate;
	private Date endDate;
	private int number;					// 培训人数
	private String teacher;
	private String descr;
	public int getTrainId()
	{
		return trainId;
	}
	public void setTrainId(int trainId)
	{
		this.trainId = trainId;
	}
	public String getTname()
	{
		return tname;
	}
	public void setTname(String tname)
	{
		this.tname = tname;
	}
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public String getTeacher()
	{
		return teacher;
	}
	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
	}
	public String getDescr()
	{
		return descr;
	}
	public void setDescr(String descr)
	{
		this.descr = descr;
	}
	@Override
	public String toString()
	{
		return "Train [trainId=" + trainId + ", tname=" + tname + ", startDate=" + startDate + ", endDate=" + endDate + ", number=" + number + ", teacher=" + teacher + ", descr=" + descr + "]";
	}
	
}
