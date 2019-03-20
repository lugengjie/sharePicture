package com.example.demo.picture.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;

public interface IPictureService
{
	//保存图片路径
	public void savePicture(PictureDTO pictureDto);
	
	//上传图片
	public boolean uploadPicture(MultipartFile multipartFile,PictureDTO picture);
	
	//根据相册Id查找图片
	public List<Picture> findPictureByAlbumId(Long albumId);

}
