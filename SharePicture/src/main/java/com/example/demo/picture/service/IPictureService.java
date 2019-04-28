package com.example.demo.picture.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public AlbumDTO pictureCarousel(Long pictureId,Long myUserId);
	
	//喜欢图片
	public boolean likePicture(Long pictureId, String email);
	
	//取消喜欢图片
	public boolean cancelLikePicture(Long pictureId, String email);
	
	//收藏图片
	public boolean collectPicture(HttpSession session,PictureDTO pictureDTO, String email);
	
	//快速收藏图片
	public boolean quickCollectPicture(HttpSession session,PictureDTO pictureDTO);
	
	//编辑图片
	public boolean editPicture(Long userId, PictureDTO pictureDTO);
	
	//删除图片
	public boolean deletePicture(Long userId, PictureDTO pictureDTO);
	
	//模糊查询图片
	public List<PictureDTO> reseachPicturesByLike(Long myUserId,String likeStr);

}
