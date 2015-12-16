package com.person.entity;

import java.util.List;

/**
 * Ȩ��ʵ����
 * ����1---��������Ա---��1��2��3��4��
 * */
public class Role
{
	private int roleId;
	private String roleName;
	// �������ԣ����ڲ�ѯʱ��װ��Ӧ��һ��ģ��
	private List<Module> modules;
	// ���ڷ�װ��ɫ��Ӧ��ģ��ID�б�
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
