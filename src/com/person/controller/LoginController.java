package com.person.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.person.dao.EmpDao;
import com.person.dao.RoleDao;
import com.person.entity.Emp;
import com.person.entity.Module;
import com.person.util.Dict;
import com.person.util.ImageUtil;

/**��¼��ع���*/
@Controller
@RequestMapping("login")
public class LoginController
{
	@Resource
	private EmpDao empDao;
	@Resource
	private RoleDao roleDao;
	
	/**�򿪵�¼ҳ��*/
	@RequestMapping("toLogin.do")
	public String toLogin()
	{
		return "main/login";
	}
	
	/**�˳���¼*/
	@RequestMapping("logout.do")
	public String logout(HttpSession session)
	{
		session.invalidate();			// ��sessionʧЧ
		return "redirect:toLogin.do";
	}

	/**��ϵͳ��ҳ*/
	@RequestMapping("toIndex.do")
	public String toIndex()
	{
		return "main/index";
	}

	@RequestMapping("checkLogin.do")
	@ResponseBody
	public int checkLogin(String username, String password, String verifyCode, HttpSession session)
	{
		// �ж���֤���Ƿ���ȷ
		String imageCode = (String) session.getAttribute("imageCode");
		if (!verifyCode.equalsIgnoreCase(imageCode))
		{
			return Dict.VERIFYCODE_ERROR;
		}
		// �����û������Ҷ�Ӧ��Emp����
		Emp emp = empDao.findEmpByUsername(username); 
		// û�ҵ����û����û�������
		if(emp == null)
		{
			return Dict.USERNAME_ERROR;
		}
		// ��¼���벻ƥ�䣬�������
		if(!password.equals(emp.getPassword()))
		{
			return Dict.PASSWORD_ERROR;
		}
		
		session.setAttribute("emp", emp);
		// ��ѯ��ǰ�û����Է��ʵ�ģ�飨ID list��������session
		List<Integer> moduleIds = new ArrayList<>();
		List<Module> modules = roleDao.findRoleById(emp.getRoleType()).getModules();
		for(Module module : modules)
		{
			moduleIds.add(module.getModuleId());
		}
		session.setAttribute("userModules", moduleIds);
		return Dict.SUCCESS;
	}

	/**������֤��ͼƬ*/
	@RequestMapping("createImage.do")
	public void createImage(HttpServletResponse response, HttpSession session) throws IOException
	{
		// ������֤��ͼƬ
		Map<String, BufferedImage> map = ImageUtil.createImage();
		// ��ȡ��֤�룬����session
		String code = map.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		// ��ȡͼƬ
		BufferedImage image = map.get(code);
		// ��ҳ�����ͼƬ
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	
	/**��Ȩ����*/
	@RequestMapping("noPower.do")
	public String noPower()
	{
		return "main/nopower";
	}

}
