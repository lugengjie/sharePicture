package com.example.demo.album.service;

import com.example.demo.account.entity.User;
import com.example.demo.album.entity.AlbumDTO;

public interface IAlbumService
{
	//添加相册
	void addAlbum(Long userId,AlbumDTO albumDto);
}
