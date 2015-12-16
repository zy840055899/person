package com.person.entity;

import java.sql.Date;

/**’–∆∏ µÃÂ¿‡*/
public class Recruitment
{
	private int recId;
	private String job;
	private String content;
	private int number;
	private String lowestEdu;
	private String sal;
	private Date issueDate;
	private String email;
	private int status;
	public int getRecId()
	{
		return recId;
	}
	public void setRecId(int recId)
	{
		this.recId = recId;
	}
	public String getJob()
	{
		return job;
	}
	public void setJob(String job)
	{
		this.job = job;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public String getLowestEdu()
	{
		return lowestEdu;
	}
	public void setLowestEdu(String lowestEdu)
	{
		this.lowestEdu = lowestEdu;
	}
	public String getSal()
	{
		return sal;
	}
	public void setSal(String sal)
	{
		this.sal = sal;
	}
	public Date getIssueDate()
	{
		return issueDate;
	}
	public void setIssueDate(Date issueDate)
	{
		this.issueDate = issueDate;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	@Override
	public String toString()
	{
		return "Recruitment [recId=" + recId + ", job=" + job + ", content=" + content + ", number=" + number + ", lowestEdu=" + lowestEdu + ", sal=" + sal + ", issueDate=" + issueDate + ", email="
				+ email + ", status=" + status + "]";
	}
	
	
}
