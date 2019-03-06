package com.example.demo.album.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String toPersonalCenter()
	{
		return "personalCenter";
	}
	
	/**
	 * 添加相册
	 * @param session
	 * @param albumDto
	 */
	@RequestMapping(value = "/addAlbum")
	public @ResponseBody void addAlbum(HttpSession session, AlbumDTO albumDto) 
	{
		albumService.addAlbum(1L, albumDto);
	}
}
