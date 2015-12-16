package com.person.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.person.dao.TrainDao;
import com.person.entity.Train;
import com.person.entity.page.TrainPage;
import com.person.util.Dict;
import com.person.util.MyDateFormat;

@Controller
@RequestMapping("train")
@SessionAttributes("trainPage")
public class TrainController
{
	@Resource
	private TrainDao trainDao;
	
	@RequestMapping("findAllTrain.do")
	public String findAllTrain(Model model, TrainPage trainPage, HttpSession session)
	{
		List<Train> trainList = trainDao.findAllTrain(trainPage);
		// 查出共有多少条数据
		int rows = trainDao.findAllTrainRows();
		trainPage.setRows(rows);
		
		model.addAttribute("trains", trainList);
		model.addAttribute("page", trainPage);
		// 将用户是否具有角色管理权限封装
		model.addAttribute("flag", 0);
		List<Integer> moduleIds = (List<Integer>) session.getAttribute("userModules");
		for(int id : moduleIds)
		{
			if(id == Dict.Module.TRAIN)
			{
				model.addAttribute("flag", 1);
				break;
			}
		}
		return "train/train_list";
	}
	
	@RequestMapping("delTrainById.do")
	public String delTrainById(int trainId)
	{
		@SuppressWarnings("unused")
		int a = trainDao.delTrainById(trainId);
		return "redirect: findAllTrain.do";
	}
	
	@RequestMapping("toAddTrain.do")
	public String toAddTrain()
	{
		return "train/train_add";
	}
	
	@RequestMapping("addTrain.do")
	public String addTrain(Train train, Model model, String startDateStr, String endDateStr)
	{
		// 将获取到的时间字符串转为date对象
		Date startDate = new Date(MyDateFormat.dateFormat(startDateStr, "yyyy-MM-dd").getTime()); 
		Date endDate = new Date(MyDateFormat.dateFormat(endDateStr, "yyyy-MM-dd").getTime());
		train.setStartDate(startDate);
		train.setEndDate(endDate);
		trainDao.addTrain(train);
		return "redirect: findAllTrain.do";
	}
	
	@RequestMapping("toModifyTrain.do")
	public String toModifyTrain(int trainId, Model model)
	{
		Train train = trainDao.findTrainById(trainId);
		model.addAttribute("train", train);
		return "train/train_modify";
	}
	
	@RequestMapping("modifyTrain.do")
	public String modifyTrain(Train train, Model model, String startDateStr, String endDateStr)
	{
		// 将获取到的时间字符串转为date对象
		Date startDate = new Date(MyDateFormat.dateFormat(startDateStr, "yyyy-MM-dd").getTime()); 
		Date endDate = new Date(MyDateFormat.dateFormat(endDateStr, "yyyy-MM-dd").getTime());
		train.setStartDate(startDate);
		train.setEndDate(endDate);
		@SuppressWarnings("unused")
		int a = trainDao.modifyTrain(train);
		return "redirect: findAllTrain.do";
	}

}
