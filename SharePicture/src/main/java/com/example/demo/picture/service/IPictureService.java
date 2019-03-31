package com.example.demo.picture.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;

public interface IPictureService
{
	//保存图片路径
	public void savePicture(PictureDTO pictureDto);
	
	//上传图片
	public boolean uploadPicture(MultipartFile multipartFile,PictureDTO picture);	
	
	//展示相册中所有图片
	public AlbumDTO showAllPictureOfAlbum(AlbumDTO albumDTO);
	
	//图片轮播
	public AlbumDTO pictureCarousel(String pictureName);

}
