package com.person.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.person.dao.RoleDao;
import com.person.entity.Module;
import com.person.entity.Role;
import com.person.entity.RoleModule;
import com.person.entity.page.RolePage;
import com.person.util.Dict;

@Controller
@RequestMapping("/role")
@SessionAttributes("rolePage")
public class RoleController{
	@Resource
	private RoleDao roleDao;
	
	/**打开登录页面*/
	@RequestMapping("findAllRole.do")
	public String findAllRole(Model model, RolePage rolePage, HttpSession session) 
	{
		List<Role> roles = roleDao.findAllRoles(rolePage);
		// 查出共有多少条数据
		int rows = roleDao.findAllRoleRows();
		rolePage.setRows(rows);
		
		model.addAttribute("roles", roles);
		model.addAttribute("page", rolePage);
		
		// 将用户是否具有角色管理权限封装
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.ROLE)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "role/role_list";
	}
	
	/**删除角色*/
	@RequestMapping("deleteRole.do")
	@ResponseBody
	public int deleteRole(int roleId)
	{
		// 先检查该角色仍然在使用的员工总数
		int sum = roleDao.findCountOfRole(roleId);
		if(sum == 0)
		{
			@SuppressWarnings("unused")
			int a = roleDao.deleteRoleById(roleId);
			// 对应的role_module表(中间表)的内容外键关联删除（cascade）
			return Dict.SUCCESS;
		}else
		return Dict.FAILURE;
	}
	
	/**修改角色权限*/
	@RequestMapping("toModifyRole.do")
	public String toModifyRole(int roleId, Model model)
	{
		//查询全部的模块，用于初始化checkbox
		List<Module> modules = roleDao.findModules();
		model.addAttribute("modules", modules);
		// 先检查该角色
		Role role = roleDao.findRoleById(roleId);
		model.addAttribute("role", role);
		return "role/role_modify";
	}
	
	@RequestMapping("modifyRole.do")
	public String modifyRole(Role role) {
		//修改角色表
		roleDao.modifyRole(role);
		//删除中间表
		roleDao.deleteRoleModule(role.getRoleId());
		//插入中间表
		int roleId = role.getRoleId();
		List<Integer> moduleIds = role.getModuleIds();
		//循环插入中间表数据
		for(int moduleId : moduleIds) {
			RoleModule rm = new RoleModule();
			rm.setRoleId(roleId);
			rm.setModuleId(moduleId);
			roleDao.addRoleModule(rm);
		}		
		return "redirect:findAllRole.do";
	}
	
	@RequestMapping("toAddRole.do")
	public String toAddRole(Model model) {
		//查询全部的模块，用于初始化checkbox
		List<Module> modules = roleDao.findModules();
		model.addAttribute("modules", modules);
		return "role/role_add";
	}
	
	@RequestMapping("addRole.do")
	public String addRole(Role role) {
		//新增角色数据
		roleDao.addRole(role);
		//新增角色模块中间表数据
		int roleId = role.getRoleId();
		List<Integer> moduleIds = role.getModuleIds();
		//循环插入中间表数据
		for(int moduleId : moduleIds) {
			RoleModule rm = new RoleModule();
			rm.setRoleId(roleId);
			rm.setModuleId(moduleId);
			roleDao.addRoleModule(rm);
		}
		return "redirect:findAllRole.do";
	}
	
	/**检查角色名是否被占用*/
	@RequestMapping("checkRoleName.do")
	@ResponseBody
	public int checkRoleName(String roleName, int roleId)
	{
		// 根据角色名称找到角色
		Role role = roleDao.findRoleByName(roleName);
		// 如果找不到，说明可以使用，找到了是本身，也可使用
		if(role == null || roleId == role.getRoleId()){
			return Dict.SUCCESS;
		}else{
			return Dict.FAILURE;
		}
	}
	
}



