package com.example.demo.album.service;

import java.util.List;

import com.example.demo.account.entity.User;
import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumDTO;

public interface IAlbumService
{
	//添加相册
	public void addAlbum(Long userId,AlbumDTO albumDto);
	//展示相册
	public List<AlbumDTO> showAlbum(Long userId);
	//展示相册和封面图片
	public List<AlbumDTO> showAlbumAndCoverPicture(Long myUserId, Long userId);
	//修改相册
	public void changeAlbum(AlbumDTO albumDTO);
	//删除相册
	public void deleteAlbum(Long albumId);
	//关注相册
	public boolean focusOnAlbum(Long albumId, Long userId);
	//取消关注相册
	public boolean cancelFocusOnAlbum(Long albumId, Long userId);
	//封装到showPictureOfAlbum的DTO
	public AlbumDTO findAlbumDtoByUserIdAndAlbumId(Long userId, Long albumId);
	//模糊查询相册
	public List<AlbumDTO> reseachAlbumsByLike(Long myUserId, String likeStr);
	
}
