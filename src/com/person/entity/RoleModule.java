package com.person.entity;

/** 
 * ��ɫ-Ȩ�޷����һ�Զࣩ
 * �����ûʲô��
 * 2015��3��26�� 11:34:41 
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
