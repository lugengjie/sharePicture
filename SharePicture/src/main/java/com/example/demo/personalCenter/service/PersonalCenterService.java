package com.example.demo.personalCenter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.personalCenter.repository.FansRepository;
import com.example.demo.picture.entity.PictureDTO;

@Service
@Transactional
public class PersonalCenterService implements IPersonalCenterService
{
	@Autowired
	FansRepository fansRepository;
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 关注用户:1.将用户的被关注属性+1， 2.就关注关系存入数据库
	 * 被关注用户不能为空，
	 * 被关注用户Id和fansId不能样，
	 * fans表中不能已有该关注关系
	 */
	public void focusOnUser(Long userId, String email)
	{
		User user = userRepository.findById(userId).get();
		Long fansId = userRepository.findByEmial(email).getId();
		if (user != null && fansId !=user.getId())
		{
			Fans fansData = fansRepository.findByUserIdAndFansId(userId, fansId);
			if(fansData == null)
			{
				int followNumber = user.getFollowNumber()+1;
				user.setFollowNumber(followNumber);
				userRepository.save(user);
				fansData = new Fans();
				fansData.setFansId(fansId);
				fansData.setUserId(userId);
				fansRepository.save(fansData);
			}
		}	
	}
	
	/**
	 * 取消关注用户:1.将用户的被关注属性-1， 2.删除数据库中的关注关系
	 * fans表中需已有该关注关系
	 */
	public void cancelFocusOnUser(Long userId, String email)
	{
		User user = userRepository.findById(userId).get();
		Long fansId = userRepository.findByEmial(email).getId();	
		Fans fansData = fansRepository.findByUserIdAndFansId(userId, fansId);
		if(fansData != null)
		{
			int followNumber = user.getFollowNumber()-1;
			user.setFollowNumber(followNumber);
			userRepository.save(user);
			fansRepository.delete(fansData);
		}
	}
	
	//跳转到首页
//	public List<PictureDTO> toHomePage(String email);
}
