package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Module;
import com.person.entity.Role;
import com.person.entity.RoleModule;
import com.person.entity.page.RolePage;

@MyRepository
public interface RoleDao
{
	/** ��ѯ���н�ɫ�Ͷ�Ӧ��Ȩ�� */
	List<Role> findAllRoles(RolePage rolePage);
	List<Role> findAllRolesForShow();
	/** ����ID��ѯ��ɫ�Ͷ�Ӧ��Ȩ�� */
	Role findRoleById(int roleId);
	/**��ѯ���н�ɫ������*/
	int findAllRoleRows();
	
	/** ��ѯĳ��Ȩ�ޱ�ʹ�õ�Ա������ */
	int findCountOfRole(int roleId);

	/** ���ݽ�ɫ����ѯ�ý�ɫ�ĸ��� */
	Role findRoleByName(String roleName);

	/** ���ݽ�ɫ��ɾ����ɫ */
	int deleteRoleById(int roleId);

	/** ��ѯȫ����module���ڳ�ʼ��checkbox */
	List<Module> findModules();

	/** �޸Ľ�ɫ */
	int modifyRole(Role role);

	/** ɾ���м��role_module */
	int deleteRoleModule(int roleId);

	/** ����м�� */
	int addRoleModule(RoleModule rm);

	/** ��ӽ�ɫ */
	int addRole(Role role);

}
