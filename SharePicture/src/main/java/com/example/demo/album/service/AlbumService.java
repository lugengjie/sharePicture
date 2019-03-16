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

@Service
@Transactional
public class AlbumService implements IAlbumService
{
	@Autowired
	private AlbumRepository albumRepository;
	
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
		if (albums!=null || !albums.isEmpty()) 
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

}
