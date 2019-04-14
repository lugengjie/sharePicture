package com.example.demo.album.controller;


import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.IAlbumService;

@Controller
@RequestMapping(value = "/album")
public class AlbumController
{
	/**
	 * 跳转到个人中心
	 */
	@Autowired
	IAlbumService albumService;
	
	
	/**
	 * 添加相册
	 * @param session
	 * @param albumDto
	 */
	@RequestMapping(value = "/addAlbum")
	public @ResponseBody String addAlbum(HttpSession session, AlbumDTO albumDto,Model model) 
	{
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		Long albumId = albums.get(0).getId();
		return albumId+"";
	}
	
	/**
	 * showPictureOfAlbum页面在选择相册模态框中中添加相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAlbumAtSelectAlbum")
	public String addAlbumAtSelectAlbumOfAddPicture(HttpSession session, AlbumDTO albumDto,Model model)
	{
		System.out.println(albumDto);
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "showPictureOfAlbum::albumUl";

	}
	
	
	/**
	 * personalCenterOfLike页面在选择相册模态框中中添加相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAlbumAtSelectAlbumOfPersonalCenterOfLike")
	public String addAlbumAtSelectAlbumOfPersonalCenterOfLike(HttpSession session, AlbumDTO albumDto,Model model)
	{
		System.out.println(albumDto);
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "personalCenterOfLike::albumUl";

	}
	
	/**
	 * personalCenterOfCollect页面在选择相册模态框中中添加相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAlbumAtSelectAlbumOfPersonalCenterOfCollect")
	public String addAlbumAtSelectAlbumOfPersonalCenterOfCollect(HttpSession session, AlbumDTO albumDto,Model model)
	{
		System.out.println(albumDto);
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "personalCenterOfCollect::albumUl";

	}
	
	/**
	 * homePage页面在选择相册模态框中中添加相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAlbumAtSelectAlbumOfHomePage")
	public String addAlbumAtSelectAlbumOfHomePage(HttpSession session, AlbumDTO albumDto,Model model)
	{
		System.out.println(albumDto);
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "homePage::albumUl";

	}
	
	/**
	 * 修改相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeAlbum")
	public @ResponseBody String changeAlbum(AlbumDTO albumDTO,Model model)
	{
		Long myUserId = 1L;
		albumService.changeAlbum(albumDTO);
		/*List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(myUserId,1L);
		model.addAttribute("albums", albums);*/
		return "修改相册成功";
	}
	
	/**
	 * 删除相册
	 * @param albumDTO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAlbum")
	public @ResponseBody String deleteAlbum(AlbumDTO albumDTO,Model model)
	{
		Long myUserId = 1L;
		albumService.deleteAlbum(albumDTO.getId());
		/*List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(myUserId,1L);
		model.addAttribute("albums", albums);*/
		return "删除相册成功";
	}
}
