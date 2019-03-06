package com.example.demo.album.service;



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
	private UserRepository userRepository;
	@Autowired
	private AlbumRepository albumRepository;
	
	/**
	 * 添加相册
	 */
	public void addAlbum(Long userId, AlbumDTO albumDto)
	{
		User user=userRepository.findById(userId).get();
		Album album=new Album();
		BeanUtils.copyProperties(albumDto,album);
		album.setUser(user);	
		user.getAlbums().add(album);
		albumRepository.save(album);
	}

}
