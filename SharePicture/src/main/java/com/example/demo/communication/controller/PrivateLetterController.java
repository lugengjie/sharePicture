package com.example.demo.communication.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.communication.entity.PrivateLetterDTO;
import com.example.demo.communication.service.PrivateLetterService;

@Controller
@RequestMapping(value = "/privateLetter")
public class PrivateLetterController
{
	@Autowired
	PrivateLetterService privateLetterService;
	
	@RequestMapping(value = "/savePrivateLetter")
	public @ResponseBody String savePrivateLetter(HttpSession session,PrivateLetterDTO privateLetterDTO)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		if(privateLetterService.savePrivateLetter(myUserId, privateLetterDTO))
		{
			return "成功";
		}
		return "失败";
	}
	
	@RequestMapping(value = "/showPrivateLetter")
	public @ResponseBody String showPrivateLetter(HttpSession session,Long otherUserId)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		return privateLetterService.findPrivateLetters(myUserId, otherUserId);
	}
	
	
	@RequestMapping(value = "/showPrivateLetterUnReaded")
	public @ResponseBody String showPrivateLetterUnReaded(HttpSession session)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		return privateLetterService.findPrivateLetterRimind(myUserId);

	}
	
	@RequestMapping(value = "/findUnreadMessageNum")
	public String findUnreadMessageNum(HttpSession session,Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		return "personalCenterOfAlbum::unReadPrivateLetterNumber";
	}
	
	@RequestMapping(value = "/setIsRead")
	public @ResponseBody String setIsRead(HttpSession session,Long otherUserId)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		privateLetterService.setIsRead(myUserId, otherUserId);
		return "成功";
	}

}
