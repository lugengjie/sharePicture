package com.example.demo.communication.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.communication.entity.PrivateLetter;
import com.example.demo.communication.entity.PrivateLetterDTO;
import com.example.demo.communication.repository.PrivateLetterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PrivateLetterService implements IPrivateLetterService
{
	@Autowired
	PrivateLetterRepository privateLetterRepository;
	@Autowired
	UserRepository userRepository;
	public boolean savePrivateLetter(Long myUserId, PrivateLetterDTO privateLetterDTO)
	{
		if(myUserId != null)
		{
			Long receiverId =privateLetterDTO.getReceiverId();
			String privateLetterWord = privateLetterDTO.getPrivateLetterWord();
			if(receiverId !=null && privateLetterWord!=null)
			{
				PrivateLetter privateLetter = new PrivateLetter();
				privateLetter.setIsReaded(0);
				privateLetter.setPrivateLetterWord(privateLetterWord);
				privateLetter.setSenderId(myUserId);
				privateLetter.setReceiverId(receiverId);
				privateLetterRepository.save(privateLetter);
				return true;
			}
		}
		return false;
	}
	
	public String findPrivateLetters(Long myUserId, Long otherUserId)
	{
		String privateLetterString = null;
		List<PrivateLetterDTO> privateLetterDTOs = null;
		if(myUserId != null && otherUserId != null)
		{
			List<PrivateLetter> privateLetters = privateLetterRepository.findPrivateLetters(myUserId, otherUserId);
			if(privateLetters != null && !privateLetters.isEmpty())
			{
				String myUserPicture = userRepository.findById(myUserId).get().getUserPicture();
				String otherUserPicture = userRepository.findById(otherUserId).get().getUserPicture();
				privateLetterDTOs = new ArrayList<PrivateLetterDTO>();
				for(PrivateLetter privateLetter:privateLetters)
				{
					privateLetterRepository.setIsRead(myUserId, otherUserId);
					PrivateLetterDTO privateLetterDTO = new PrivateLetterDTO();
					privateLetterDTO.setPrivateLetterWord(privateLetter.getPrivateLetterWord());
					if(privateLetter.getSenderId().equals(myUserId))
					{
						privateLetterDTO.setSenderPicture(myUserPicture);
					}
					else
					{
						privateLetterDTO.setReceiverPicture(otherUserPicture);
					}
					privateLetterDTOs.add(privateLetterDTO);
				}
				ObjectMapper mapper = new ObjectMapper();
				try
				{
					privateLetterString = mapper.writeValueAsString(privateLetterDTOs);
				} 
				catch (JsonProcessingException e)
				{
					e.printStackTrace();
				}
			}
			
			return privateLetterString;
		}
		return privateLetterString;
	}
	
	/**
	 * 返回消息提醒
	 */
	public String findPrivateLetterRimind(Long myUserId)
	{
		List<PrivateLetterDTO> privateLetterDTOs = null;
		String privateLetterRemindString = null;
		if(myUserId != null) 
		{
			List<PrivateLetter> privateLetterReminds = privateLetterRepository.findPrivateLetterRimind(myUserId);
			if(privateLetterReminds != null && !privateLetterReminds.isEmpty())
			{			
				privateLetterDTOs = new ArrayList<PrivateLetterDTO>();
				for(PrivateLetter privateLetterRemind:privateLetterReminds)
				{
					User sender = userRepository.findById(privateLetterRemind.getSenderId()).get();
					PrivateLetterDTO privateLetterDTO = new PrivateLetterDTO();
					privateLetterDTO.setPrivateLetterWord(privateLetterRemind.getPrivateLetterWord());
					privateLetterDTO.setSenderId(sender.getId());
					privateLetterDTO.setSenderName(sender.getName());
					privateLetterDTO.setSenderPicture(sender.getUserPicture());
					privateLetterDTO.setReceiverId(myUserId);
					privateLetterDTOs.add(privateLetterDTO);
				}
				ObjectMapper mapper = new ObjectMapper();
				try
				{
					privateLetterRemindString = mapper.writeValueAsString(privateLetterDTOs);
				} 
				catch (JsonProcessingException e)
				{
					e.printStackTrace();
				}
				return privateLetterRemindString;
			}
		}
		return privateLetterRemindString;
	}
	
	//查找未读信息数量
    public int findUnreadMessageNum(Long myUserId)
    {
    	int unreadMessageNum = 0;
    	if(myUserId != null)
    	{
    		unreadMessageNum=privateLetterRepository.findUnreadMessageNum(myUserId);
    	}
    	return unreadMessageNum;
    }
    
    //修改已读信息
  	public void setIsRead(Long myUserId, Long otherUserId)
  	{
  		if(myUserId != null && otherUserId != null)
  		{
  			privateLetterRepository.setIsRead(myUserId, otherUserId);
  		}
  	}
}
