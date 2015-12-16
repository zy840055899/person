package com.person.entity;

public class Salary
{
	private int salId;			// н����ϢID
	private int empId;			// Ա��ID
	private String monthFor;	// ���ŵ��·�
	private int basicSal=0;		// �������ʣ�����Ա���Ļ���н�ʵȼ��͵��µĹ���ʱ��/�ܵ�Ӧ�ù���ʱ��ó���
	private int extraSal=0;		// �Ӱಹ��
	private int travelSal=0;	// �����
	private int forfeit=0;		// ��������ٵ��ȣ�
	// ��������
	private int sumSal;			// ���µ�н�ʻ���
	private String name;		// Ա������
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSalId()
	{
		return salId;
	}
	public void setSalId(int salId)
	{
		this.salId = salId;
	}
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public String getMonthFor()
	{
		return monthFor;
	}
	public void setMonthFor(String monthFor)
	{
		this.monthFor = monthFor;
	}
	public int getBasicSal()
	{
		return basicSal;
	}
	public void setBasicSal(int basicSal)
	{
		this.basicSal = basicSal;
	}
	public int getExtraSal()
	{
		return extraSal;
	}
	public void setExtraSal(int extraSal)
	{
		this.extraSal = extraSal;
	}
	public int getTravelSal()
	{
		return travelSal;
	}
	public void setTravelSal(int travelSal)
	{
		this.travelSal = travelSal;
	}
	public int getForfeit()
	{
		return forfeit;
	}
	public void setForfeit(int forfeit)
	{
		this.forfeit = forfeit;
	}
	// н���ܺ� = ����н��+�����+�Ӱಹ��-����
	public int getSumSal()
	{
		return basicSal+travelSal+extraSal-forfeit;
	}
	public void setSumSal(int sumSal)
	{
		this.sumSal = sumSal;
	}
	@Override
	public String toString()
	{
		return "Salary [salId=" + salId + ", empId=" + empId + ", monthFor=" + monthFor + ", basicSal=" + basicSal + ", extraSal=" + extraSal + ", travelSal=" + travelSal + ", forfeit=" + forfeit
				+ ", sumSal=" + sumSal + ", name=" + name + "]";
	}
}
