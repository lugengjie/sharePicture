package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.personalCenter.service.IPersonalCenterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalCenterTest
{
	@Autowired
	IPersonalCenterService personalCenterService;
	
	@Test
	public void focusOnUser()
	{
		personalCenterService.focusOnUser(1L, "xueyuancpt@163.com");
	}
	
	@Test
	public void cancelFocusOnUser()
	{
		personalCenterService.cancelFocusOnUser(7L, "xueyuancpt@163.com");
	}

}
