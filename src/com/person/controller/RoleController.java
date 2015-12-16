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
	
	/**�򿪵�¼ҳ��*/
	@RequestMapping("findAllRole.do")
	public String findAllRole(Model model, RolePage rolePage, HttpSession session) 
	{
		List<Role> roles = roleDao.findAllRoles(rolePage);
		// ������ж���������
		int rows = roleDao.findAllRoleRows();
		rolePage.setRows(rows);
		
		model.addAttribute("roles", roles);
		model.addAttribute("page", rolePage);
		
		// ���û��Ƿ���н�ɫ����Ȩ�޷�װ
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
	
	/**ɾ����ɫ*/
	@RequestMapping("deleteRole.do")
	@ResponseBody
	public int deleteRole(int roleId)
	{
		// �ȼ��ý�ɫ��Ȼ��ʹ�õ�Ա������
		int sum = roleDao.findCountOfRole(roleId);
		if(sum == 0)
		{
			@SuppressWarnings("unused")
			int a = roleDao.deleteRoleById(roleId);
			// ��Ӧ��role_module��(�м��)�������������ɾ����cascade��
			return Dict.SUCCESS;
		}else
		return Dict.FAILURE;
	}
	
	/**�޸Ľ�ɫȨ��*/
	@RequestMapping("toModifyRole.do")
	public String toModifyRole(int roleId, Model model)
	{
		//��ѯȫ����ģ�飬���ڳ�ʼ��checkbox
		List<Module> modules = roleDao.findModules();
		model.addAttribute("modules", modules);
		// �ȼ��ý�ɫ
		Role role = roleDao.findRoleById(roleId);
		model.addAttribute("role", role);
		return "role/role_modify";
	}
	
	@RequestMapping("modifyRole.do")
	public String modifyRole(Role role) {
		//�޸Ľ�ɫ��
		roleDao.modifyRole(role);
		//ɾ���м��
		roleDao.deleteRoleModule(role.getRoleId());
		//�����м��
		int roleId = role.getRoleId();
		List<Integer> moduleIds = role.getModuleIds();
		//ѭ�������м������
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
		//��ѯȫ����ģ�飬���ڳ�ʼ��checkbox
		List<Module> modules = roleDao.findModules();
		model.addAttribute("modules", modules);
		return "role/role_add";
	}
	
	@RequestMapping("addRole.do")
	public String addRole(Role role) {
		//������ɫ����
		roleDao.addRole(role);
		//������ɫģ���м������
		int roleId = role.getRoleId();
		List<Integer> moduleIds = role.getModuleIds();
		//ѭ�������м������
		for(int moduleId : moduleIds) {
			RoleModule rm = new RoleModule();
			rm.setRoleId(roleId);
			rm.setModuleId(moduleId);
			roleDao.addRoleModule(rm);
		}
		return "redirect:findAllRole.do";
	}
	
	/**����ɫ���Ƿ�ռ��*/
	@RequestMapping("checkRoleName.do")
	@ResponseBody
	public int checkRoleName(String roleName, int roleId)
	{
		// ���ݽ�ɫ�����ҵ���ɫ
		Role role = roleDao.findRoleByName(roleName);
		// ����Ҳ�����˵������ʹ�ã��ҵ����Ǳ���Ҳ��ʹ��
		if(role == null || roleId == role.getRoleId()){
			return Dict.SUCCESS;
		}else{
			return Dict.FAILURE;
		}
	}
	
}



