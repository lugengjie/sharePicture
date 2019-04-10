package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.album.entity.Album;
import com.example.demo.personalCenter.repository.FansRepository;
import com.example.demo.personalCenter.service.IPersonalCenterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalCenterTest
{
	@Autowired
	IPersonalCenterService personalCenterService;
	@Autowired
	private FansRepository fansRepository;
	
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
	
	@Test
	public void findAlbumIdsOfUsersByFansId()
	{
		System.out.println(fansRepository.findAlbumIdsOfUsersByFansId(7L));
	}
	
//	@Test
//	public void findPictureDTOsOfUserByAlbumIds()
//	{
//		List<Long> albumIds=new ArrayList<Long>();
//		albumIds.add(1L);
//		albumIds.add(74L);
//		System.out.println(personalCenterService.findPictureDTOsOfUserByAlbumIds(albumIds));
////		System.out.println(fansRepository.findPictureDTOsOfUserByAlbumIds(albumIds));
//		
//	}
	
	
}
