package com.person.util;

/**
 * 提示码字典
 * 2015年3月20日 10:40:48
 */
public interface Dict
{
	static final int SUCCESS = 0;			// 成功码
	static final int USERNAME_ERROR = 1;	// 用户名错误
	static final int PASSWORD_ERROR = 2;	// 密码错误
	static final int VERIFYCODE_ERROR = 3;	// 验证码错误
	static final int FAILURE = 4;			// 失败码
	
	/**权限列表*/
	interface Module
	{
		static final int ROLE = 10;			// 权限管理
		static final int EMPLOYEE = 11;		// 员工管理
		static final int DEPT = 12;			// 部门管理
		static final int SALARY = 13;		// 薪资管理
		static final int ATTEND = 14;		// 考勤管理
		static final int TRAIN = 15;		// 部门管理
		static final int RECRUITMENT = 16;	// 招聘管理
	}
	
	/**薪资划分表*/
	interface Salary
	{
		static final int SAL_A = 10000;
		static final int SAL_B = 8000;
		static final int SAL_C = 6000;
		static final int SAL_D = 4000;
		static final int SAL_E = 2000;
		static final int SAL_EXTRA = 30;	// 加班补助（每小时）
		static final int SAL_TRAVEL = 80;	// 出差补助（每天）
	}
	
}
