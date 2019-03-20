package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.Album;
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
	
	@Test
	public void showAlbumTest()
	{
		albumService.showAlbum(1L);
	}
	
	@Test
	public void showAlbumAndCoverPictureTest()
	{
		List<AlbumDTO> albumDtos=albumService.showAlbumAndCoverPicture(1L);
		for(AlbumDTO albumDTO:albumDtos)
		{
			System.out.println(albumDTO);
		}
	}
	
	@Test
	public void changeAlbum() 
	{
		AlbumDTO albumDTO=new AlbumDTO();
		albumDTO.setId(1L);
		albumDTO.setAlbumTitle("卡卡");
		albumService.changeAlbum(albumDTO);
	}
}
