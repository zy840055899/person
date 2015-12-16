package com.person.util;

import com.person.exception.SalCalculatrException;

public class SalaryCaculator
{
	/**
	 * 2015��5��3�� 21:35:35
	 * @param salGrade��н�ʵȼ�
	 * @param workdayCount������Ӧ�ÿ�������
	 * @param sumWorkTime��ʵ�ʿ����ܵĹ���ʱ��
	 * @throws SalCalculatrException 
	 * @return������н��
	 */
	public static int calculatrBasicSal(String salGrade, int workdayCount, int sumWorkTime) throws SalCalculatrException
	{
		int basicSal = 0;
		switch (salGrade)
		{
		case "A":
			basicSal = Dict.Salary.SAL_A;
			break;
		case "B":
			basicSal = Dict.Salary.SAL_B;
			break;
		case "C":
			basicSal = Dict.Salary.SAL_C;
			break;
		case "D":
			basicSal = Dict.Salary.SAL_D;
			break;
		case "E":
			basicSal = Dict.Salary.SAL_E;
			break;
		default:
			throw new SalCalculatrException("û���ҵ��õȼ��Ļ���н��");
		}
		double result = sumWorkTime * 1.0 / (workdayCount * 8) * basicSal;
		return (int) Math.round(result);
	}
}
