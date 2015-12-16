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

/**登录相关功能*/
@Controller
@RequestMapping("login")
public class LoginController
{
	@Resource
	private EmpDao empDao;
	@Resource
	private RoleDao roleDao;
	
	/**打开登录页面*/
	@RequestMapping("toLogin.do")
	public String toLogin()
	{
		return "main/login";
	}
	
	/**退出登录*/
	@RequestMapping("logout.do")
	public String logout(HttpSession session)
	{
		session.invalidate();			// 令session失效
		return "redirect:toLogin.do";
	}

	/**打开系统首页*/
	@RequestMapping("toIndex.do")
	public String toIndex()
	{
		return "main/index";
	}

	@RequestMapping("checkLogin.do")
	@ResponseBody
	public int checkLogin(String username, String password, String verifyCode, HttpSession session)
	{
		// 判断验证码是否正确
		String imageCode = (String) session.getAttribute("imageCode");
		if (!verifyCode.equalsIgnoreCase(imageCode))
		{
			return Dict.VERIFYCODE_ERROR;
		}
		// 根据用户名查找对应的Emp对象
		Emp emp = empDao.findEmpByUsername(username); 
		// 没找到该用户，用户名错误
		if(emp == null)
		{
			return Dict.USERNAME_ERROR;
		}
		// 登录密码不匹配，密码错误
		if(!password.equals(emp.getPassword()))
		{
			return Dict.PASSWORD_ERROR;
		}
		
		session.setAttribute("emp", emp);
		// 查询当前用户可以访问的模块（ID list），放入session
		List<Integer> moduleIds = new ArrayList<>();
		List<Module> modules = roleDao.findRoleById(emp.getRoleType()).getModules();
		for(Module module : modules)
		{
			moduleIds.add(module.getModuleId());
		}
		session.setAttribute("userModules", moduleIds);
		return Dict.SUCCESS;
	}

	/**生成验证码图片*/
	@RequestMapping("createImage.do")
	public void createImage(HttpServletResponse response, HttpSession session) throws IOException
	{
		// 创建验证码图片
		Map<String, BufferedImage> map = ImageUtil.createImage();
		// 获取验证码，放入session
		String code = map.keySet().iterator().next();
		session.setAttribute("imageCode", code);
		// 获取图片
		BufferedImage image = map.get(code);
		// 向页面输出图片
		response.setContentType("image/jpeg");
		OutputStream os = response.getOutputStream();
		ImageIO.write(image, "jpeg", os);
		os.close();
	}
	
	/**无权访问*/
	@RequestMapping("noPower.do")
	public String noPower()
	{
		return "main/nopower";
	}

}
