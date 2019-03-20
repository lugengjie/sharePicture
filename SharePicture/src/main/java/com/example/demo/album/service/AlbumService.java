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
import com.example.demo.album.repository.AlbumRepository;
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
				List<Picture> pictures=pictureRepository.findPictureByUserId(album.getId());
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
					for(int i=pictures.size()-2;i>=0;i--)
					{
						int j=0;
						String pictureName=pictures.get(i).getPictureName();
						albumDto.getPictureNames().set(j++,pictureName);
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
}
