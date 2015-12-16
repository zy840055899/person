package com.person.entity;

import java.sql.Date;

/** 考勤实体类 */
public class Attend
{
	private int attendId; // 签到id
	private int empId; // 员工号
	private Date dutyDate; // 工作日期
	private int isTravel; // 是否出差
	private int workTime; // 工作时长
	private int extraTime; // 加班时长
	private String remark; // 备注
	private int isApproved; // 是否通过
	private String groupDate; // 分组date

	// 表外关联属性
	private String name; // 员工姓名

	public String getGroupDate()
	{
		return groupDate;
	}

	public void setGroupDate(String groupDate)
	{
		this.groupDate = groupDate;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAttendId()
	{
		return attendId;
	}

	public void setAttendId(int attendId)
	{
		this.attendId = attendId;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public Date getDutyDate()
	{
		return dutyDate;
	}

	public void setDutyDate(Date dutyDate)
	{
		this.dutyDate = dutyDate;
	}

	public int getIsTravel()
	{
		return isTravel;
	}

	public void setIsTravel(int isTravel)
	{
		this.isTravel = isTravel;
	}

	public int getWorkTime()
	{
		return workTime;
	}

	public void setWorkTime(int workTime)
	{
		this.workTime = workTime;
	}

	public int getExtraTime()
	{
		return extraTime;
	}

	public void setExtraTime(int extraTime)
	{
		this.extraTime = extraTime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public int getIsApproved()
	{
		return isApproved;
	}

	public void setIsApproved(int isApproved)
	{
		this.isApproved = isApproved;
	}

	@Override
	public String toString()
	{
		return "Attend [attendId=" + attendId + ", empId=" + empId + ", dutyDate=" + dutyDate + ", isTravel=" + isTravel + ", workTime=" + workTime + ", extraTime=" + extraTime + ", remark=" + remark
				+ ", isApproved=" + isApproved + ", groupDate=" + groupDate + ", name=" + name + "]";
	}
}
