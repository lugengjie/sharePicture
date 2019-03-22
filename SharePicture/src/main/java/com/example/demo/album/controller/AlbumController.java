package com.example.demo.album.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping(value="/personal")
	public String toPersonalCenter(HttpSession session,Model model)
	{
		List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(1L);
		model.addAttribute("albums", albums);
		return "personalCenter";
	}
	
	/**
	 * 添加相册
	 * @param session
	 * @param albumDto
	 */
	@RequestMapping(value = "/addAlbum")
	public String addAlbum(HttpSession session, AlbumDTO albumDto,Model model) 
	{
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "addPicture";
	}
	
	/**
	 * 在选择相册模态框中中添加相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addAlbumAtSelectAlbum")
	public String addAlbumAtSelectAlbum(HttpSession session, AlbumDTO albumDto,Model model)
	{
		System.out.println(albumDto);
		albumService.addAlbum(1L, albumDto);
		List<AlbumDTO> albums=albumService.showAlbum(1L);
		model.addAttribute("albums", albums);
		return "addPicture::albumUl";

	}
	
	/**
	 * 修改相册
	 * @param session
	 * @param albumDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeAlbum")
	public String changeAlbum(AlbumDTO albumDTO,Model model)
	{
		albumService.changeAlbum(albumDTO);
		List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(1L);
		model.addAttribute("albums", albums);
		return "personalCenter";
	}
	
	/**
	 * 删除相册
	 * @param albumDTO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAlbum")
	public String deleteAlbum(AlbumDTO albumDTO,Model model)
	{
		albumService.deleteAlbum(albumDTO.getId());
		List<AlbumDTO> albums=albumService.showAlbumAndCoverPicture(1L);
		model.addAttribute("albums", albums);
		return "personalCenter";
	}
}
