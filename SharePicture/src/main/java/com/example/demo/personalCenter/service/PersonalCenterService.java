package com.example.demo.personalCenter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.repository.FocusOnAlbumRepository;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.personalCenter.repository.FansRepository;
import com.example.demo.picture.entity.PictureDTO;

@Service
@Transactional
public class PersonalCenterService implements IPersonalCenterService
{
	@Autowired
	FansRepository fansRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AlbumRepository albumRepository;
	@Autowired
	FocusOnAlbumRepository focusOnAlbumRepository;
	
	/**
	 * 关注用户:1.将用户的被关注属性+1， 2.就关注关系存入数据库
	 * 被关注用户不能为空，
	 * 被关注用户Id和fansId不能样，
	 * fans表中不能已有该关注关系
	 */
	public void focusOnUser(Long userId, String email)
	{
		User user = userRepository.findById(userId).get();
		Long fansId = userRepository.findByEmial(email).getId();
		if (user != null && fansId !=user.getId())
		{
			Fans fansData = fansRepository.findByUserIdAndFansId(userId, fansId);
			if(fansData == null)
			{
				int followNumber = user.getFollowNumber()+1;
				user.setFollowNumber(followNumber);
				userRepository.save(user);
				fansData = new Fans();
				fansData.setFansId(fansId);
				fansData.setUserId(userId);
				fansRepository.save(fansData);
			}
		}	
	}
	
	/**
	 * 取消关注用户:1.将用户的被关注属性-1， 2.删除数据库中的关注关系
	 * fans表中需已有该关注关系
	 */
	public void cancelFocusOnUser(Long userId, String email)
	{
		User user = userRepository.findById(userId).get();
		Long fansId = userRepository.findByEmial(email).getId();	
		Fans fansData = fansRepository.findByUserIdAndFansId(userId, fansId);
		if(fansData != null)
		{
			int followNumber = user.getFollowNumber()-1;
			user.setFollowNumber(followNumber);
			userRepository.save(user);
			fansRepository.delete(fansData);
		}
	}
	
	/**
	 * 根据albumIds封装PicttureDTO
	 */
	public List<PictureDTO> findPictureDTOsOfUserByAlbumIds(List<Long> albumIds)
	{
		List<PictureDTO> pictureDTOs = null;
		if(albumIds!=null && !albumIds.isEmpty())
		{
			List<Object> objectTemps = fansRepository.findPictureDTOsOfUserByAlbumIds(albumIds);
			if(objectTemps!=null && !objectTemps.isEmpty())
			{
				pictureDTOs=new ArrayList<PictureDTO>();
				for(Object objectTemp:objectTemps)
				{
					Object[] pictureDTOTemp=(Object[])objectTemp;
					PictureDTO pictureDTO = new PictureDTO();
					pictureDTO.setPictureId((Long)pictureDTOTemp[0]);
					pictureDTO.setPictureDescribe((String)pictureDTOTemp[1]);
					pictureDTO.setPictureName((String)pictureDTOTemp[2]);
					pictureDTO.setAlbumId((Long)pictureDTOTemp[3]);
					pictureDTO.setAlbumName((String)pictureDTOTemp[4]);
					pictureDTO.setUserId((Long)pictureDTOTemp[5]);
					pictureDTO.setUserName((String)pictureDTOTemp[6]);
					pictureDTOs.add(pictureDTO);
				}
			}
		}
		return pictureDTOs; 
	}
	
	/**
	 * 封装发送到HomePage的PictureDTOs
	 * 1.查询用户的相册id,关注的人的相册id,关注的相册的id
	 * 2.将相册id查询的数据封装成PictureDTOs
	 */
	public List<PictureDTO> HomePageOfPictureDTOs(String email)
	{
		User user = userRepository.findByEmial(email);
		List<PictureDTO> pictureDTOs = null;
		if(user != null)
		{
			Long userId = user.getId();
			List<Long> albumIds = new ArrayList<Long>();
			List<Long> albumIdsOfUser = albumRepository.findAlbumIdsByUserId(userId);
			List<Long> albumIdsOfFansed = fansRepository.findAlbumIdsOfUsersByFansId(userId);
			List<Long> albumIdsOfFocusedAlbum = focusOnAlbumRepository.findFocusOnAlbumIdsByUserId(userId);
			if(albumIdsOfUser != null && !albumIdsOfUser.isEmpty())
			{
				albumIds.addAll(albumIdsOfUser);
			}
			if(albumIdsOfFansed != null && !albumIdsOfFansed.isEmpty())
			{
				albumIds.addAll(albumIdsOfFansed);
			}
			if(albumIdsOfFocusedAlbum != null && !albumIdsOfFocusedAlbum.isEmpty())
			{
				albumIds.addAll(albumIdsOfFocusedAlbum);
			}
			pictureDTOs = findPictureDTOsOfUserByAlbumIds(albumIds);
		}
		return pictureDTOs;
	}
}
