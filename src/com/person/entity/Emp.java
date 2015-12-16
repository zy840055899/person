package com.person.entity;

import java.sql.Date;

/**
 * 员工实体类
 * 2015年2月26日 19:57:50
 */
public class Emp
{
	private int empId;
	private String name;
	private String username;
	private String password;
	private String gender;
	private String nativee;			// 员工籍贯
	private Date birthday;
	private int deptId;
	private String title;			// 员工职位
	private String workPhone;
	private String mobile;
	private String email;
	private String education;
	private String marry;
	private String identityId;
	private String address;
	private String icon;			// 头像地址
	private Date enterDate;
	private Date lastLoginDate;
	private int roleType;
	private String salGrade;
	
	/** 表外属性，关联表的字段*/
	private String deptName;
	
	
	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getNativee()
	{
		return nativee;
	}

	public void setNativee(String nativee)
	{
		this.nativee = nativee;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public int getDeptId()
	{
		return deptId;
	}

	public void setDeptId(int deptId)
	{
		this.deptId = deptId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getWorkPhone()
	{
		return workPhone;
	}

	public void setWorkPhone(String workPhone)
	{
		this.workPhone = workPhone;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEducation()
	{
		return education;
	}

	public void setEducation(String education)
	{
		this.education = education;
	}

	public String getMarry()
	{
		return marry;
	}

	public void setMarry(String marry)
	{
		this.marry = marry;
	}

	public String getIdentityId()
	{
		return identityId;
	}

	public void setIdentityId(String identityId)
	{
		this.identityId = identityId;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Date getEnterDate()
	{
		return enterDate;
	}

	public void setEnterDate(Date enterDate)
	{
		this.enterDate = enterDate;
	}

	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public int getRoleType()
	{
		return roleType;
	}

	public void setRoleType(int roleType)
	{
		this.roleType = roleType;
	}

	public String getSalGrade()
	{
		return salGrade;
	}

	public void setSalGrade(String salGrade)
	{
		this.salGrade = salGrade;
	}

	@Override
	public String toString()
	{
		return "Emp [empId=" + empId + ", name=" + name + ", username=" + username + ", password=" + password + ", gender=" + gender + ", nativee=" + nativee + ", birthday=" + birthday + ", deptId="
				+ deptId + ", title=" + title + ", workPhone=" + workPhone + ", mobile=" + mobile + ", email=" + email + ", education=" + education + ", marry=" + marry + ", identityId=" + identityId
				+ ", address=" + address + ", icon=" + icon + ", enterDate=" + enterDate + ", lastLoginDate=" + lastLoginDate + ", roleType=" + roleType + ", salGrade=" + salGrade + "]";
	}
}
