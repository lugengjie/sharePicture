package com.example.demo.personalCenter.service;

import java.util.List;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.personalCenter.entity.UserSettingDTO;
import com.example.demo.picture.entity.PictureDTO;

public interface IPersonalCenterService
{
	//关注用户
	public void focusOnUser(Long userId, Long fansId);
	
	//取消关注用户
	public void cancelFocusOnUser(Long userId, Long fansId);
	
	//根据AlbumIds封装PictureDTOs
	public List<PictureDTO> findPictureDTOsOfUserByAlbumIds(List<Long> albumIds, Long userId);
	
	// 封装发送到HomePage的PictureDTOs
	public List<PictureDTO> homePageOfPictureDTOs(String email);
	
	//封装发送到HomePage的UserDTO
	public UserDTO homePageOfUserDTOs(String email);
	
	//封装发送到personalCenterOfAlbum的UserDTO
	public UserDTO personalCenterOfAlbumOfUserDTOs(Long myUserId, Long userId);
	
	//封装发送到personalCenterOfLike的PictureDTOs
	public List<PictureDTO> personalCenterOfLike(Long myUserId, Long userId);
	
	//封装发送到personalCenterOfFans的UserDTOs
	public List<UserDTO> personalCenterOfFans(Long myUserId, Long userId);
	
	//封装发送到personalCenterOfFocusOn的UserDTOs
	public List<UserDTO> personalCenterOfFocusOnOfUserDTOs(Long myUserId, Long userId);
	
	//封装发送到personalCenterOfFocusOn的AlbumDTOs
	public List<AlbumDTO> personalCenterOfFocusOnOfAlbumDTOs(Long myUserId, Long userId);
	
	//封装发送到UserSetting的UserSettingDTO
	public UserSettingDTO toUserSetting(Long myUserId);
	
	
}
