package com.example.demo.personalCenter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.entity.UserDTO;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.repository.FocusOnAlbumRepository;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.personalCenter.repository.FansRepository;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.repository.LikePictureRepository;
import com.example.demo.picture.repository.PictureRepository;

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
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	LikePictureRepository likePictureRepository;
	
	/**
	 * 关注用户:1.将用户的被关注属性+1， 2.就关注关系存入数据库
	 * 被关注用户不能为空，
	 * 被关注用户Id和fansId不能一样，
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
				int fansNumber = user.getFansNumber()+1;
				user.setFansNumber(fansNumber);
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
			int fansNumber = user.getFansNumber()-1;
			user.setFansNumber(fansNumber);
			userRepository.save(user);
			fansRepository.delete(fansData);
		}
	}
	
	/**
	 * 根据albumIds封装PicttureDTO
	 */
	public List<PictureDTO> findPictureDTOsOfUserByAlbumIds(List<Long> albumIds, Long userId)
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
					pictureDTO.setLikeNumber((int)pictureDTOTemp[3]);
					pictureDTO.setCollectNumber((int)pictureDTOTemp[4]);
					pictureDTO.setPictureLabel((String)pictureDTOTemp[5]);
					pictureDTO.setAlbumId((Long)pictureDTOTemp[6]);
					pictureDTO.setAlbumName((String)pictureDTOTemp[7]);
					pictureDTO.setUserId((Long)pictureDTOTemp[8]);
					pictureDTO.setIsLike(0);
					if(pictureDTO.getUserId() == userId)
					{
						//该图片是用户自己的
						pictureDTO.setIsMine(1);
					}
					else
					{
						//该图片不是用户自己的
						pictureDTO.setIsMine(0);
					}
					pictureDTO.setUserName((String)pictureDTOTemp[9]);
					pictureDTO.setUserPicture((String)pictureDTOTemp[10]);
					pictureDTOs.add(pictureDTO);
				}
				
				//pictureDTO中id在用户喜欢的图片id中，则将改pictureDTO的isLike属性设为1，否则设为0
				List<Long> likePictureIds = likePictureRepository.findLikePictureIdsByUserId(userId);
				if(likePictureIds !=null && !likePictureIds.isEmpty())
				{
					for(PictureDTO pictureDTO : pictureDTOs)
					{
						if(likePictureIds.contains(pictureDTO.getPictureId()))
						{
							pictureDTO.setIsLike(1);
						}
					}
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
	public List<PictureDTO> homePageOfPictureDTOs(String email)
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
			pictureDTOs = findPictureDTOsOfUserByAlbumIds(albumIds, userId);
		}
		return pictureDTOs;
	}
	
	
	/**
	 * 封装到HomePage的UserDTO
	 */
	public UserDTO homePageOfUserDTOs(String email)
	{
		User user = userRepository.findByEmial(email);
		UserDTO userDTO = null;
		if(user!=null)
		{
			userDTO = new UserDTO();
			Long userId = user.getId();
			int albumNumber = albumRepository.findAlbumNumberByUserId(userId);
			int fansNumber = fansRepository.findFansNumberByUserId(userId);
			int findPictureNumber =pictureRepository.findPictureNumberByUserId(userId);
			userDTO.setAlbumNumber(albumNumber);
			userDTO.setCollectionNumber(findPictureNumber);
			userDTO.setFansNumber(fansNumber);
			userDTO.setUserName(user.getName());
			userDTO.setUserPicture(user.getUserPicture());
		}
		return userDTO;
	}
	
	/**
	 * 封装发送到personalCenterOfAlbum的UserDTO
	 */
	public UserDTO personalCenterOfAlbumOfUserDTOs(Long myUserId, Long userId)
	{
		User user = userRepository.findById(userId).get();
		UserDTO userDTO = null;
		if(user!=null)
		{
			userDTO = new UserDTO();
			int albumNumber = albumRepository.findAlbumNumberByUserId(userId);
			int fansNumber = fansRepository.findFansNumberByUserId(userId);
			int findPictureNumber =pictureRepository.findPictureNumberByUserId(userId);
			userDTO.setAlbumNumber(albumNumber);
			userDTO.setCollectionNumber(findPictureNumber);
			userDTO.setFansNumber(fansNumber);
			userDTO.setUserName(user.getName());
			userDTO.setUserPicture(user.getUserPicture());
			int focusOnNumber = fansRepository.findFocusOnNumberByUserId(userId);
			userDTO.setFocusOnNumber(focusOnNumber);
			//判断是否是自己
			if(myUserId != userId)
			{
				userDTO.setIsMyUser(0);
			}
			else
			{
				userDTO.setIsMyUser(1);
			}
			Fans fans = fansRepository.findByUserIdAndFansId(userId, myUserId);
			//判断是否已关注
			if(fans != null)
			{
				userDTO.setIsFocusOn(1);
			}
			else
			{
				userDTO.setIsFocusOn(0);
			}
		}
		return userDTO;
	}
}
