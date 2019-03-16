package com.example.demo.picture.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.picture.entity.PictureDTO;

public interface IPictureService
{
	//保存图片路径
	public void savePicture(PictureDTO pictureDto);
	
	//上传图片
	public boolean uploadPicture(MultipartFile multipartFile,PictureDTO picture);

}
