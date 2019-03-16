package com.example.demo.picture.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.album.entity.Album;
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

}
