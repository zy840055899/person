package com.person.util;

/**
 * ��ʾ���ֵ�
 * 2015��3��20�� 10:40:48
 */
public interface Dict
{
	static final int SUCCESS = 0;			// �ɹ���
	static final int USERNAME_ERROR = 1;	// �û�������
	static final int PASSWORD_ERROR = 2;	// �������
	static final int VERIFYCODE_ERROR = 3;	// ��֤�����
	static final int FAILURE = 4;			// ʧ����
	
	/**Ȩ���б�*/
	interface Module
	{
		static final int ROLE = 10;			// Ȩ�޹���
		static final int EMPLOYEE = 11;		// Ա������
		static final int DEPT = 12;			// ���Ź���
		static final int SALARY = 13;		// н�ʹ���
		static final int ATTEND = 14;		// ���ڹ���
		static final int TRAIN = 15;		// ���Ź���
		static final int RECRUITMENT = 16;	// ��Ƹ����
	}
	
	/**н�ʻ��ֱ�*/
	interface Salary
	{
		static final int SAL_A = 10000;
		static final int SAL_B = 8000;
		static final int SAL_C = 6000;
		static final int SAL_D = 4000;
		static final int SAL_E = 2000;
		static final int SAL_EXTRA = 30;	// �Ӱಹ����ÿСʱ��
		static final int SAL_TRAVEL = 80;	// �������ÿ�죩
	}
	
}
