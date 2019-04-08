package com.example.demo.personalCenter.service;

import java.util.List;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.picture.entity.PictureDTO;

public interface IPersonalCenterService
{
	//关注用户
	public void focusOnUser(Long userId, String email);
	
	//取消关注用户
	public void cancelFocusOnUser(Long userId, String email);
	
	//根据AlbumIds封装PictureDTOs
	public List<PictureDTO> findPictureDTOsOfUserByAlbumIds(List<Long> albumIds);
	
	// 封装发送到HomePage的PictureDTOs
	public List<PictureDTO> homePageOfPictureDTOs(String email);
	
	//封装发送到HomePage的UserDTO
	public UserDTO homePageOfUserDTOs(String email);
}
