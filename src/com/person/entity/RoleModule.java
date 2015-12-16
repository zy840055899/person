package com.person.entity;

/** 
 * 角色-权限分配表（一对多）
 * 这个类没什么用
 * 2015年3月26日 11:34:41 
 * */
public class RoleModule
{
	private Integer roleId;
	private Integer moduleId;
	public Integer getRoleId()
	{
		return roleId;
	}
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
	public Integer getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(Integer moduleId)
	{
		this.moduleId = moduleId;
	}
	@Override
	public String toString()
	{
		return "RoleModule [roleId=" + roleId + ", moduleId=" + moduleId + "]";
	}
}
