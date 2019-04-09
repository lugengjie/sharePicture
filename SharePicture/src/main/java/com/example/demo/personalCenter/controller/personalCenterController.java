package com.example.demo.personalCenter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.IAlbumService;
import com.example.demo.personalCenter.service.PersonalCenterService;
import com.example.demo.picture.entity.PictureDTO;

@Controller
@RequestMapping(value = "/personalCenter")
public class personalCenterController
{
	@Autowired
	PersonalCenterService personalCenterService;
	@Autowired
	IAlbumService albumService;
	
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
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		return "homepage";
	}
}
