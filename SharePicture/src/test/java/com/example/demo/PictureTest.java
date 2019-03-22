package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.repository.PictureRepository;
import com.example.demo.picture.service.IPictureService;
import com.example.demo.picture.service.PictureService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureTest
{
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	IPictureService PictureService;
	
	@Test
	public void findPictureByUserId()
	{
		/*List<Picture> pictures;
		pictures=pictureRepository.findPictureByUserId(1L);
		for(Picture picture:pictures)
		{
			System.out.println(picture);
		}*/
	}
	
	@Test
	public void showAllPictureOfAlbum()
	{
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(1L);
		AlbumDTO temp=PictureService.showAllPictureOfAlbum(albumDTO);
		for(String pictureName:temp.getPictureNames())
		{
			System.out.println(pictureName);
		}
	}

}
