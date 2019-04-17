package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.recommend.service.RecommendService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendTest
{	
	@Autowired
	RecommendService recommendService;
	
	@Test
	public void findPictureDTOByInterestNameAndLimitNumber()
	{
		List<PictureDTO> pictures = recommendService.findPictureDTOByInterestNameAndLimitNumber(1L, "UI/UX", 10);
		System.out.println(pictures.size());
		
	}
	
	@Test
	public void RecommendPageOfPictureDTOTest()
	{
		List<PictureDTO> pictures = recommendService.RecommendPageOfPictureDTO(7L);
		System.out.println(pictures.size());
		
	}

}
