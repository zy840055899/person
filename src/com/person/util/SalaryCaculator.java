package com.person.util;

import com.person.exception.SalCalculatrException;

public class SalaryCaculator
{
	/**
	 * 2015年5月3日 21:35:35
	 * @param salGrade：薪资等级
	 * @param workdayCount：该月应该考勤天数
	 * @param sumWorkTime：实际考勤总的工作时长
	 * @throws SalCalculatrException 
	 * @return：基本薪资
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
			throw new SalCalculatrException("没有找到该等级的基本薪资");
		}
		double result = sumWorkTime * 1.0 / (workdayCount * 8) * basicSal;
		return (int) Math.round(result);
	}
}
