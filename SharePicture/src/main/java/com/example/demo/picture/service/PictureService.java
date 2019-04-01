package com.example.demo.picture.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.service.AlbumService;
import com.example.demo.common.utils.FileUploadUtil;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.repository.PictureRepository;

@Service
@Transactional
public class PictureService implements IPictureService
{
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Value("${web.upload-path}")
	String localAbsolutePath;
	
	/**
	 * 保存图片路径
	 */
	public void savePicture(PictureDTO pictureDto)
	{
		Picture picture=new Picture();
		BeanUtils.copyProperties(pictureDto, picture);
		pictureRepository.save(picture);		
	}
	
	/**
	 * 上传图片
	 * 1.判断文件是否为空
	 * 2.判断文件是否为图片
	 * 3.上传文件到本地
	 * 4.处理图片与相册的关系
	 */
	public boolean uploadPicture(MultipartFile multipartFile,PictureDTO picture)
	{
		if(multipartFile==null)
		{	
			return false;
		}
		if(!FileUploadUtil.isImage(multipartFile))
		{
			return false;
		}
		String fileName=FileUploadUtil.upLoad(multipartFile, localAbsolutePath);
		if(fileName==null)
		{
			return false;
		}
		picture.setPictureName(fileName);
		savePicture(picture);
		return true;
	}
	
	/**
	 * 展示相册中所有图片
	 */
	public AlbumDTO showAllPictureOfAlbum(AlbumDTO albumDTO)
	{
		Long albumId=albumDTO.getId();
		AlbumDTO temp=new AlbumDTO();
		BeanUtils.copyProperties(albumDTO, temp);
		if(albumId>0) 
		{
			List<Picture> pictures=pictureRepository.findPictureByAlbumId(albumId);
			if (pictures != null && !pictures.isEmpty())
			{
				Collections.reverse(pictures);
				for(Picture picture:pictures)
				{
					temp.getPictureNames().add(picture.getPictureName());
				}
			}
		}
		return temp;	
	}
	
	/**
	 * 图片轮播
	 * 1.根据图片路径查询图片所在的相册，相册所在用户
	 * 2.把请求中的图片所在相册的位置的前的不超过8张图片（包括该图片）加该图片后的所有图片放在PictureDTO中
	 * 3.将所有信息放在AlbumDTO中返回
	 */
	public AlbumDTO pictureCarousel(String pictureName)
	{
		Picture picture=pictureRepository.findPictureByPictureName(pictureName);	
		AlbumDTO albumDTO=new AlbumDTO();
		if(picture != null)
		{		
			albumDTO.setCoverPictureName(pictureName);
			albumDTO.setId(picture.getAlbumId());
			Album album = albumRepository.findAlbumByAlbumId(picture.getAlbumId());		
			albumDTO.setAlbumTitle(album.getAlbumTitle());
			User user = userRepository.findUserByUserId(album.getUserId());		
			albumDTO.setUserName(user.getName());
			List<Picture> pictures=pictureRepository.findPictureByAlbumId(picture.getAlbumId());
			Collections.reverse(pictures);
			int index = pictures.indexOf(picture);	
			for(int i=index;i>=0;i--)
			{
				if(index-i>=8)
				{
					break;
				}
				PictureDTO pictureDTO = new PictureDTO();
				BeanUtils.copyProperties(pictures.get(i), pictureDTO);
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
			Collections.reverse(albumDTO.getPictureDTOs());
			albumDTO.setIndex(albumDTO.getPictureDTOs().indexOf(picture));
			for(int j=index+1;j<pictures.size();j++)
			{
				PictureDTO pictureDTO = new PictureDTO();
				BeanUtils.copyProperties(pictures.get(j), pictureDTO);
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
		}
		return albumDTO;
	}

}
