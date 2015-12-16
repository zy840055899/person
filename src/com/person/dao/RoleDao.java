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
	/** 查询所有角色和对应的权限 */
	List<Role> findAllRoles(RolePage rolePage);
	List<Role> findAllRolesForShow();
	/** 根据ID查询角色和对应的权限 */
	Role findRoleById(int roleId);
	/**查询所有角色的总数*/
	int findAllRoleRows();
	
	/** 查询某个权限被使用的员工人数 */
	int findCountOfRole(int roleId);

	/** 根据角色名查询该角色的个数 */
	Role findRoleByName(String roleName);

	/** 根据角色号删除角色 */
	int deleteRoleById(int roleId);

	/** 查询全部的module用于初始化checkbox */
	List<Module> findModules();

	/** 修改角色 */
	int modifyRole(Role role);

	/** 删除中间表role_module */
	int deleteRoleModule(int roleId);

	/** 添加中间表 */
	int addRoleModule(RoleModule rm);

	/** 添加角色 */
	int addRole(Role role);

}
