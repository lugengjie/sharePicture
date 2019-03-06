package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.service.AlbumService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumTest
{
	@Autowired
	private AlbumService albumService;
	@Autowired
	private UserRepository userRepository;
	@Test
	public void addAlbumTest(){
		AlbumDTO albumDto=new AlbumDTO();
		albumDto.setAlbumTitle("开心");
		albumDto.setAlbumClassification("美女");
		albumService.addAlbum(1L, albumDto);
	}
	
	@Test
	public void findUserTest()
	{		
		System.out.println(userRepository.findById(1L).get());
	}
}
