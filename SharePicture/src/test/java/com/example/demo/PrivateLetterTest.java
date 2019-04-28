package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.communication.entity.PrivateLetter;
import com.example.demo.communication.repository.PrivateLetterRepository;
import com.example.demo.recommend.service.RecommendService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrivateLetterTest
{
	@Autowired
	PrivateLetterRepository privateLetterRepository;
	
	@Test
	public void findPrivateLetters()
	{
		List<PrivateLetter> privateLetters = privateLetterRepository.findPrivateLetters(7L, 1L);
		System.out.println(privateLetters);
	}
}
