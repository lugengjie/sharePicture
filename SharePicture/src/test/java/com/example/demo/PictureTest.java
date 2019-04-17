package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;
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
	IPictureService pictureService;
	
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
		AlbumDTO temp=pictureService.showAllPictureOfAlbum(albumDTO);
		for(String pictureName:temp.getPictureNames())
		{
			System.out.println(pictureName);
		}
	}
	
//	@Test
//	public void pictureCarousel() 
//	{
//		pictureService.pictureCarousel("/132/5a2360515ec847fa8c85deba02d96881.jpeg");
//	}
	
	@Test
	public void likePictureTest()
	{
		pictureService.likePicture(1L, "1923808485@qq.com");
	}
	
	@Test
	public void cancelLikePicture()
	{
		pictureService.cancelLikePicture(1L, "1923808485@qq.com");
	}
	
//	@Test
//	public void collectPicture()
//	{
//		PictureDTO pictureDTO = new PictureDTO();
//		pictureDTO.setAlbumId(1L);
//		pictureDTO.setPictureName("aa");
//		pictureDTO.setPictureId(1L);
//		pictureService.collectPicture(pictureDTO, "xueyuancpt@163.com");
//	}
//	
	@Test
	public void findPictureNumberByUserIdTest()
	{
		System.out.println(pictureRepository.findPictureNumberByUserId(1L));
	}
	
/*	@Test
	public void findPictureDTOByInterestNameAndLimitNumberTest()
	{
		PageRequest pageable =  PageRequest.of(0, 10);
		pictureRepository.findPictureDTOByInterestNameAndLimitNumber("UI/UX", pageable);

	}*/

}
