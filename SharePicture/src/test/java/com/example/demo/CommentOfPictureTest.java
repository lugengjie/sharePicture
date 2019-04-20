package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.communication.service.CommentOfPictureService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentOfPictureTest
{
	@Autowired
	CommentOfPictureService commentOfPictureService;
	
	@Test
	public void commentsOfPictureTOJsonTest()
	{
		String temp = commentOfPictureService.commentsOfPictureTOJson(88L);
		System.out.println(temp);
	}
}
