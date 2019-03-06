package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.entity.Check;
import com.example.demo.account.entity.User;
import com.example.demo.account.repository.CheckRepository;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.account.service.AccountService;
import com.example.demo.common.utils.SendEmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharePictureApplicationTests
{
	@Autowired
	AccountService accountService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CheckRepository checkRepository;

	@Test
	public void saveUserTest()
	{
		User user = new User();
		user.setEmail("xueyuancpt@163.com");
		user.setName("卢耿杰");
		user.setPassword("1234");
		accountService.saveUser(user);
	}

	@Test
	public void emialIsExistTest()
	{
		System.out.println(accountService.emialIsExist("1923808485@qq.com"));
	}

	@Test
	public void saveAccountAndSendEmialTest()
	{
		User user = userRepository.findByEmial("xueyuancpt@163.com");
		System.out.println(user.getAlbums().get(0));
	}

	@Test
	public void findByUserId()
	{
		Check check = checkRepository.findCheckByUserId(1L);
		System.out.println(check);
	}

	@Test
	public void findCheckByActivateCodeTest()
	{
		Check check = checkRepository.findCheckByActivateCode("41b3d3bd648d42cfaa0903da8df06c1a");
		System.out.println(check);
	}

	@Test
	public void changePasswordTest()
	{
		AccountDTO a = new AccountDTO();
		a.setEmail("1923808485@qq.com");
		a.setPassword("234");
		accountService.changePassword(a);
	}
}
