package com.example.demo.album.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.entity.FocusOnAlbum;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.repository.FocusOnAlbumRepository;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.repository.PictureRepository;

@Service
@Transactional
public class AlbumService implements IAlbumService
{
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private FocusOnAlbumRepository focusOnAlbumRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 添加相册
	 */
	public void addAlbum(Long userId, AlbumDTO albumDto)
	{
		Album album=new Album();
		BeanUtils.copyProperties(albumDto,album);
		album.setUserId(userId);	
		albumRepository.save(album);
	}
	
	/**
	 * 展示相册
	 */
	public List<AlbumDTO> showAlbum(Long userId)
	{
		List<Album> albums=albumRepository.findAlbumsByUserId(userId);
		if (albums!=null && !albums.isEmpty()) 
		{
			List<AlbumDTO> albumDtos = new ArrayList<AlbumDTO>();
			for (Album album : albums) {
				AlbumDTO albumDto = new AlbumDTO();
				BeanUtils.copyProperties(album, albumDto);
				albumDtos.add(albumDto);
			}
			return albumDtos;
		}
		return null;
	}
	
	/**
	 * 展示相册和封面图片
	 * 1.根据usrId查询相册，反转List，使最新的在前面
	 * 2.循环，根据albumId查询图片名，反转List，使最新的在前面
	 * 3.将结果封装到DTO中
	 * 4.判断是否是自己的相册
	 * 5.判断是否已关注该相册
	 */
	public List<AlbumDTO> showAlbumAndCoverPicture(Long myUserId, Long userId)
	{
		List<Album> albums=albumRepository.findAlbumsByUserId(userId);
		if(albums != null && !albums.isEmpty()) 
		{
			List<AlbumDTO> albumDtos=new ArrayList<AlbumDTO>();
			Collections.reverse(albums);
			for(Album album:albums)
			{
				AlbumDTO albumDto=new AlbumDTO();
				BeanUtils.copyProperties(album, albumDto);
				//判断是否是自己的相册
				if(!userId.equals(myUserId))
				{
					albumDto.setIsMyAlbum(0);
				}
				else
				{
					albumDto.setIsMyAlbum(1);
				}
				//判断是否已关注该相册
				FocusOnAlbum focusOnAlbum = focusOnAlbumRepository.findFocusOnAlbumByAlbumIdAndUserId(album.getId(), myUserId);
				if(focusOnAlbum == null)
				{
					albumDto.setIsFocusOn(0);
				}
				else
				{
					albumDto.setIsFocusOn(1);
				}
				List<Picture> pictures=pictureRepository.findPictureByAlbumId(album.getId());
				albumDto.setCoverPictureName("");
				for(int i=0;i<3;i++)
				{
					albumDto.getPictureNames().add("");
				}
				if(pictures != null && !pictures.isEmpty()) 
				{
					//将图片List的最后一张图片作为相册的封面
					String coverPictureName=pictures.get(pictures.size()-1).getPictureName();
					albumDto.setCoverPictureName(coverPictureName);
					int j=0;
					for(int i=pictures.size()-2;i>=0;i--)
					{
						String pictureName=pictures.get(i).getPictureName();
						albumDto.getPictureNames().set(j++,pictureName);
						if(j>2)
						{
							break;
						}
					}
				}
				albumDtos.add(albumDto);
			}
			return albumDtos;
		}
		return null;	
	}
	
	/**
	 * 修改相册
	 */
	public void changeAlbum(AlbumDTO albumDTO)
	{
		albumRepository.changeAlbum(albumDTO.getAlbumTitle(), albumDTO.getAlbumClassification(),albumDTO.getAlbumDescribe(), albumDTO.getId());
	}
	
	/**
	 * 根据相册Id删除相册
	 */
	public void deleteAlbum(Long albumId)
	{
		albumRepository.deleteAlbum(albumId);
	}
	
	/**
	 * 关注相册
	 * 1.判断相册是否为空
	 * 2.判断关注关系是否已存在
	 * 3.将相册的被关注数+1
	 * 4.就关注关系存入数据库
	 * 5.相册不能为自己的相册
	 */
	public boolean focusOnAlbum(Long albumId, Long userId)
	{
		Album album = albumRepository.findById(albumId).get();
		Album albumTemp = albumRepository.findAlbumByAlbumIdAndUserId(albumId, userId);
		if(albumTemp == null)
		{
			if(userId != null && userId !=0)
			{
				if (album != null)
				{
					FocusOnAlbum focusOnAlbum = focusOnAlbumRepository.findFocusOnAlbumByAlbumIdAndUserId(albumId, userId);
					if(focusOnAlbum == null)
					{
						int focusNumber = album.getFocusNumber()+1;
						album.setFocusNumber(focusNumber);
						albumRepository.save(album);
						focusOnAlbum = new FocusOnAlbum();
						focusOnAlbum.setAlbumId(albumId);
						focusOnAlbum.setUserId(userId);
						focusOnAlbumRepository.save(focusOnAlbum);	
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 取消关注相册
	 * 1.相册不能为空
	 * 2.判断关注关系需已存在
	 * 3.将相册的被关注数-1
	 * 4.将关注关系删除
	 */
	public boolean cancelFocusOnAlbum(Long albumId, Long userId)
	{
		Album album = albumRepository.findById(albumId).get();
		if(userId != null && userId !=0)
		{
			if (album != null)
			{
				FocusOnAlbum focusOnAlbum = focusOnAlbumRepository.findFocusOnAlbumByAlbumIdAndUserId(albumId, userId);
				if(focusOnAlbum != null)
				{
					int focusNumber = album.getFocusNumber()-1;
					album.setFocusNumber(focusNumber);
					albumRepository.save(album);
					focusOnAlbumRepository.delete(focusOnAlbum);	
				}
			}
		}
		return true;
	}
	/**
	 * 封装到showPictureOfAlbum的AlbumDTO
	 */
	public AlbumDTO findAlbumDtoByUserIdAndAlbumId(Long userId, Long albumId)
	{
		AlbumDTO albumDTO = null;
		if(albumId!=null && albumId !=0)
		{
			Album album = albumRepository.findById(albumId).get();
			if(album !=null)
			{

				albumDTO = new AlbumDTO();
				BeanUtils.copyProperties(album, albumDTO);
				Long userIdTemp = album.getUserId();
				User user = userRepository.findById(userIdTemp).get();
				//判断是否是用户的相册
				if(!user.getId().equals(userId))
				{
					albumDTO.setIsMyAlbum(0);
				}
				else
				{
					albumDTO.setIsMyAlbum(1);
				}
				albumDTO.setUserName(user.getName());
				albumDTO.setUserPicture(user.getUserPicture());
				FocusOnAlbum focusOnAlbum =focusOnAlbumRepository.findFocusOnAlbumByAlbumIdAndUserId(albumId, userId);
				if(focusOnAlbum == null)
				{
					albumDTO.setIsFocusOn(0);
				}
				else
				{
					albumDTO.setIsFocusOn(1);
				}
				//相册中的图片数
				int pictureNumber = 0;
				List<Picture> pictures = pictureRepository.findPictureByAlbumId(albumId);
				if(pictures!=null && !pictures.isEmpty())
				{
					pictureNumber = pictures.size();
				}
				albumDTO.setPictureNumber(pictureNumber);
				return albumDTO;
			}
			
		}
		return albumDTO;
	}
	
	//模糊查询相册
	public List<AlbumDTO> reseachAlbumsByLike(Long myUserId, String likeStr)
	{
		List<Album> albums = albumRepository.reseachAlbumsByLike(likeStr);
		if(albums != null && !albums.isEmpty()) 
		{
			List<AlbumDTO> albumDtos=new ArrayList<AlbumDTO>();
			for(Album album:albums)
			{
				AlbumDTO albumDto=new AlbumDTO();
				BeanUtils.copyProperties(album, albumDto);
				//判断是否是自己的相册
				if(!album.getUserId().equals(myUserId))
				{
					albumDto.setIsMyAlbum(0);
				}
				else
				{
					albumDto.setIsMyAlbum(1);
				}
				//判断是否已关注该相册
				FocusOnAlbum focusOnAlbum = focusOnAlbumRepository.findFocusOnAlbumByAlbumIdAndUserId(album.getId(), myUserId);
				if(focusOnAlbum == null)
				{
					albumDto.setIsFocusOn(0);
				}
				else
				{
					albumDto.setIsFocusOn(1);
				}
				List<Picture> pictures=pictureRepository.findPictureByAlbumId(album.getId());
				albumDto.setCoverPictureName("");
				for(int i=0;i<3;i++)
				{
					albumDto.getPictureNames().add("");
				}
				if(pictures != null && !pictures.isEmpty()) 
				{
					//将图片List的最后一张图片作为相册的封面
					String coverPictureName=pictures.get(pictures.size()-1).getPictureName();
					albumDto.setCoverPictureName(coverPictureName);
					int j=0;
					for(int i=pictures.size()-2;i>=0;i--)
					{
						String pictureName=pictures.get(i).getPictureName();
						albumDto.getPictureNames().set(j++,pictureName);
						if(j>2)
						{
							break;
						}
					}
				}
				albumDtos.add(albumDto);
			}
			return albumDtos;
		}
		return null;	
	}
	
}
