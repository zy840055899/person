package com.person.entity;

import java.util.List;

/**
 * 权限实体类
 * 例：1---超级管理员---（1，2，3，4）
 * */
public class Role
{
	private int roleId;
	private String roleName;
	// 关联属性，用于查询时封装对应的一组模块
	private List<Module> modules;
	// 用于封装角色对应的模块ID列表
	private List<Integer> moduleIds;
	
	public List<Integer> getModuleIds()
	{
		return moduleIds;
	}
	public void setModuleIds(List<Integer> moduleIds)
	{
		this.moduleIds = moduleIds;
	}
	public int getRoleId()
	{
		return roleId;
	}
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	public List<Module> getModules()
	{
		return modules;
	}
	public void setModules(List<Module> modules)
	{
		this.modules = modules;
	}
	@Override
	public String toString()
	{
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", modules=" + modules + ", moduleIds=" + moduleIds + "]";
	}
}
