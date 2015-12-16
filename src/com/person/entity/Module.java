package com.person.entity;

/**È¨ÏÞ±í*/
public class Module 
{
	private Integer moduleId;
	private String moduleName;
	public Integer getModuleId()
	{
		return moduleId;
	}
	public void setModuleId(Integer moduleId)
	{
		this.moduleId = moduleId;
	}
	public String getModuleName()
	{
		return moduleName;
	}
	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}
	@Override
	public String toString()
	{
		return "Module [moduleId=" + moduleId + ", moduleName=" + moduleName + "]";
	}
	
}
