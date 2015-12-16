package com.person.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.person.dao.RecruitmentDao;
import com.person.entity.Recruitment;
import com.person.entity.page.RecruitmentPage;
import com.person.util.Dict;

@Controller
@RequestMapping("recruit")
@SessionAttributes("recruitmentPage")
public class RecruitmentController
{
	@Resource
	private RecruitmentDao recDao;
	
	@RequestMapping("findAllRec.do")
	public String findAllRec(Model model, RecruitmentPage recruitmentPage, HttpSession session)
	{
		// 按页查找
		List<Recruitment> recLists = recDao.findAllRec(recruitmentPage);
		// 查出所有数据的条数
		int rows = recDao.findAllRecRows();
		recruitmentPage.setRows(rows);
		
		model.addAttribute("recruits", recLists);
		model.addAttribute("page", recruitmentPage);
		// 查询用户是否有该权限
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.RECRUITMENT)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "recruitment/recruit_list";
	}
	
	@RequestMapping("delRecById.do")
	public String delRecById(int recId, Model model)
	{
		recDao.delRecById(recId);
		return "redirect: findAllRec.do";
	}
	
	@RequestMapping("toModifyRec.do")
	public String toModifyRec(int recId, Model model)
	{
		Recruitment rec = recDao.findRecById(recId);
		model.addAttribute("rec", rec);
		return "recruitment/recruit_modify";
	}
	
	@RequestMapping("modifyRec.do")
	public String modifyRec(Recruitment rec)
	{
		recDao.modifyRec(rec);
		return "redirect: findAllRec.do";
	}
	
	@RequestMapping("toAddRec.do")
	public String toAddRec()
	{
		return "recruitment/recruit_add";
	}
	
	@RequestMapping("addRec.do")
	public String addRec(Recruitment rec)
	{
		rec.setStatus(0);
		recDao.addRec(rec);
		return "redirect:findAllRec.do";
	}
	
	@RequestMapping("changeStatus.do")
	public String changeStatus(int recId, int status)
	{
		Map<String, Integer> map = new HashMap<>();
		map.put("id", recId);
		map.put("status", status);
		recDao.changeStatus(map);
		return "redirect:findAllRec.do";
	}
}
