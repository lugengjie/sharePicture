package com.example.demo.personalCenter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.personalCenter.service.PersonalCenterService;
import com.example.demo.picture.entity.PictureDTO;

@Controller
@RequestMapping(value = "/personalCenter")
public class personalCenterController
{
	@Autowired
	PersonalCenterService personalCenterService;
	
	/**
	 * 跳转到homePage
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toHomePage")
	public String toHomePage(HttpSession session, Model model)
	{
		List<PictureDTO> pictureDTOs = personalCenterService.homePageOfPictureDTOs("xueyuancpt@163.com");
		UserDTO userDTO = personalCenterService.homePageOfUserDTOs("xueyuancpt@163.com");
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		return "homepage";
	}
}
