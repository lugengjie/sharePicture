package com.example.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.personalCenter.entity.Interest;
import com.example.demo.personalCenter.repository.InterestRepository;
import com.example.demo.personalCenter.service.InterestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterestTest
{
	@Autowired
	InterestRepository interestRepository;
	@Autowired
	InterestService interestService;
//	@Test
//	public void saveTest() 
//	{
//		Interest interest = new Interest();
//		interest.setUserId(1L);
//		List<String> interestName = interest.getInterestName();
//		interestName.add("aa");
//		interestService.saveInteresetService(interest);
//	}
}
