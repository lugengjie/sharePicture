package com.example.demo.personalCenter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.account.service.AccountService;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.IAlbumService;
import com.example.demo.communication.service.PrivateLetterService;
import com.example.demo.personalCenter.entity.UserSettingDTO;
import com.example.demo.personalCenter.service.InterestService;
import com.example.demo.personalCenter.service.PersonalCenterService;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.repository.PictureRepository;
import com.example.demo.picture.service.PictureService;

@Controller
@RequestMapping(value = "/personalCenter")
public class personalCenterController
{
	@Autowired
	PersonalCenterService personalCenterService;
	@Autowired
	IAlbumService albumService;
	@Autowired
	InterestService interestService;
	@Autowired
	PrivateLetterService privateLetterService;
	@Autowired
	PictureService pictureService;
	@Autowired
	AccountService accountService;
	/*
	 * 跳转到PersonalCenterOfAlbum
	 */
	@RequestMapping(value="/personalCenterOfAlbum")
	public String toPersonalCenterOfAlbum(HttpSession session,Long userId, Model model)
	{
		
		Long myUserId = (Long) session.getAttribute("userId");
		List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(myUserId, userId);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
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
		Long myUserId = (Long) session.getAttribute("userId");
		List<AlbumDTO> albums=albumService.showAlbum(userId);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<PictureDTO> pictureDTOs = personalCenterService.personalCenterOfCollect(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		model.addAttribute("userDTO", userDTO);
		return "personalCenterOfCollect";
	}
	
	
	/*
	 * 跳转到PersonalCenterOfLike
	 */
	@RequestMapping(value="/personalCenterOfLike")
	public String toPersonalCenterOfLike(HttpSession session,Long userId, Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		List<AlbumDTO> albums=albumService.showAlbum(myUserId);
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<PictureDTO> pictureDTOs = personalCenterService.personalCenterOfLike(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(userId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(userId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		model.addAttribute("userDTO", userDTO);
		return "personalCenterOfLike";
	}
	
	
	/*
	 * 跳转到PersonalCenterOfFans
	 */
	@RequestMapping(value="/personalCenterOfFans")
	public String toPersonalCenterOfFans(HttpSession session,Long userId, Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<UserDTO> fansDTOs = personalCenterService.personalCenterOfFans(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(userId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(userId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("fansDTOs", fansDTOs);
		return "personalCenterOfFans";
	}
	
	/*
	 * 跳转到PersonalCenterOfFoucusOn
	 */
	@RequestMapping(value="/personalCenterOfFocusOnAlbum")
	public String toPersonalCenterOfFocusOnAlbum(HttpSession session,Long userId, Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<AlbumDTO> albumDTOs = personalCenterService.personalCenterOfFocusOnOfAlbumDTOs(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(userId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(userId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("albums", albumDTOs);
		return "personalCenterOfFocusOnAlbum";
	}
	
	@RequestMapping(value="/personalCenterOfFocusOnUser")
	public String toPersonalCenterOfFocusOnUser(HttpSession session,Long userId, Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		UserDTO userDTO = personalCenterService.personalCenterOfAlbumOfUserDTOs(myUserId, userId);
		List<UserDTO> focusOnDTOs = personalCenterService.personalCenterOfFocusOnOfUserDTOs(myUserId, userId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(userId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(userId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("focusOnDTOs", focusOnDTOs);
		return "personalCenterOfFocusOnUser";
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
		UserDTO myUserDTO = personalCenterService.navUserDTO(userId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(userId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userDTO", userDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		model.addAttribute("albums", albums);
		return "homepage";
	}
	
	@RequestMapping(value = "/focusOnUser")
	public @ResponseBody String focusOnUser(Long userId, HttpSession session)
	{
		Long fansId =(Long) session.getAttribute("userId");
		personalCenterService.focusOnUser(userId, fansId);
		return "成功";
	}
	
	@RequestMapping(value = "/cancelFocusOnUser")
	public @ResponseBody String cancelFocusOnUser(Long userId, HttpSession session)
	{
		Long fansId =(Long) session.getAttribute("userId");
		personalCenterService.cancelFocusOnUser(userId, fansId);
		return "成功";
	}
	
	@RequestMapping(value = "/userSetting")
	public @ResponseBody String userSetting(MultipartFile multipartFile, HttpSession session, UserSettingDTO userSettingDTO)
	{
		Long myUserId =(Long) session.getAttribute("userId");
		for(int i=0;i<10;i++)System.out.println(userSettingDTO);
		if(interestService.userSetting(multipartFile, myUserId, userSettingDTO))
		{
			return myUserId+"";
		}
		return "失败";
	}
	
	@RequestMapping(value = "/toUserSetting")
	public String toUserSetting(HttpSession session, Model model)
	{
		Long myUserId =(Long) session.getAttribute("userId");
		UserSettingDTO userSettingDTO = personalCenterService.toUserSetting(myUserId);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userSettingDTO", userSettingDTO);
		return "userSetting";
	}
	
	@RequestMapping(value = "/tosearchAlbum")
	public String tosearchAlbum(HttpSession session,String likeStr, Model model)
	{
		Long myUserId =(Long) session.getAttribute("userId");
		List<AlbumDTO> albums= albumService.reseachAlbumsByLike(myUserId, likeStr);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("albums", albums);
		return "researchAlbum";
	}
	
	@RequestMapping(value = "/tosearchPicture")
	public String tosearchPicture(HttpSession session,String likeStr, Model model)
	{
		Long myUserId =(Long) session.getAttribute("userId");
		List<PictureDTO> pictureDTOs = pictureService.reseachPicturesByLike(myUserId, likeStr);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("pictureDTOs", pictureDTOs);
		return "researchPicture";
		
	}
	
	@RequestMapping(value = "/tosearchUser")
	public String tosearchUser(HttpSession session,String likeStr, Model model)
	{
		Long myUserId =(Long) session.getAttribute("userId");
		List<UserDTO> userDTOs = accountService.researchUser(myUserId, likeStr);
		UserDTO myUserDTO = personalCenterService.navUserDTO(myUserId);
		int unreadMessageNum = privateLetterService.findUnreadMessageNum(myUserId);
		model.addAttribute("unReadMessageNum", unreadMessageNum+"");
		model.addAttribute("myUserDTO", myUserDTO);
		model.addAttribute("userDTOs", userDTOs);
		return "researchUser";
		
	}
	
	@RequestMapping(value = "/destroySession")
	public @ResponseBody String destroySession(HttpSession session)
	{
		session.invalidate();
		return "成功";
	}

}
