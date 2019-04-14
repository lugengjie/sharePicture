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
	
	/*
	 * 跳转到PersonalCenterOfAlbum
	 */
	@RequestMapping(value="/personalCenterOfAlbum")
	public String toPersonalCenterOfAlbum(HttpSession session,Long userId, Model model)
	{
		Long myUserId = 1L;
		userId = 1L;
		List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(myUserId, userId);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		model.addAttribute("albums", albums);
		model.addAttribute("userDTO", userDTO);
		return "personalCenterOfAlbum";
	}
	
	
	/*
	 * 跳转到PersonalCenterOfCollect
	 */
	@RequestMapping(value="/personalCenterOfCollect")
	public String toPersonalCenterOfCollect(HttpSession session,Long userId, Model model)
	{
		Long myUserId = 1L;
		userId = 1L;
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<PictureDTO> pictureDTOs = personalCenterService.personalCenterOfCollect(myUserId, userId);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		model.addAttribute("userDTO", userDTO);
		return "personalCenterOfCollect";
	}
	
	
	/*
	 * 跳转到PersonalCenterOfLike
	 */
	@RequestMapping(value="personalCenterOfLike")
	public String toPersonalCenterOfLike(HttpSession session,Long userId, Model model)
	{
		Long myUserId = 1L;
		userId = 1L;
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<PictureDTO> pictureDTOs = personalCenterService.personalCenterOfLike(myUserId, userId);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		model.addAttribute("userDTO", userDTO);
		return "personalCenterOfLike";
	}
	
	/**
	 * 跳转到homePage
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toHomePage")
	public String toHomePage(HttpSession session, Model model)
	{
		Long userId =(Long) session.getAttribute("userId");
		String email =(String)session.getAttribute("email");
		List<PictureDTO> pictureDTOs = personalCenterService.homePageOfPictureDTOs(email);
		UserDTO userDTO = personalCenterService.homePageOfUserDTOs(email);
		List<AlbumDTO> albums=albumService.showAlbum(userId);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		return "homepage";
	}
	
	
}
