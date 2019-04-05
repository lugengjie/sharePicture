package com.example.demo.personalCenter.service;

import java.util.List;

import com.example.demo.picture.entity.PictureDTO;

public interface IPersonalCenterService
{
	//关注用户
	public void focusOnUser(Long userId, String email);
	
	//取消关注用户
	public void cancelFocusOnUser(Long userId, String email);
	
	//跳转到首页
//	public List<PictureDTO> toHomePage(String email);
}
