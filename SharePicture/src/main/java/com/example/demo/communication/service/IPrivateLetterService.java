package com.example.demo.communication.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.communication.entity.PrivateLetterDTO;

public interface IPrivateLetterService
{
	public boolean savePrivateLetter(Long myUserId, PrivateLetterDTO privateLetterDTO);
	
	//返回两个人的所有聊天记录
	public String findPrivateLetters(Long myUserId, Long otherUserId);
	
	//返回消息提醒
	public String findPrivateLetterRimind(Long myUserId);
	
	//查找未读信息数量
	public int findUnreadMessageNum(Long myUserId);
	
	//修改已读信息
	public void setIsRead(Long myUserId, Long otherUserId);
}
