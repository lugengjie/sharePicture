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
			Collections.reverse(albumDtos);
			return albumDtos;
		}
		return null;
	}
	
	/**
	 * 展示相册和封面图片
	 * 1.根据usrId查询相册，反转List，使最新的在前面
	 * 2.循环，根据albumId查询图片名，反转List，使最新的在前面
	 * 3.将结果封装到DTO中
	 */
	public List<AlbumDTO> showAlbumAndCoverPicture(Long userId)
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
	 */
	public void focusOnAlbum(Long albumId, String email)
	{
		Album album = albumRepository.findById(albumId).get();
		Long userId = userRepository.findByEmial(email).getId();
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
			}
		}	
	}

	/**
	 * 取消关注相册
	 * 1.相册不能为空
	 * 2.判断关注关系需已存在
	 * 3.将相册的被关注数-1
	 * 4.将关注关系删除
	 */
	public void cancelFocusOnAlbum(Long albumId, String email)
	{
		Album album = albumRepository.findById(albumId).get();
		Long userId = userRepository.findByEmial(email).getId();
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
	
}
