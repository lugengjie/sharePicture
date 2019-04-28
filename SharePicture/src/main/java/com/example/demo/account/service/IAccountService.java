package com.example.demo.account.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.entity.Check;
import com.example.demo.account.entity.User;
import com.example.demo.account.entity.UserDTO;

public interface IAccountService
{
	public void saveUser(User user);

	public boolean emialIsExist(String email);

	public void saveAccountAndSendEmial(AccountDTO account);

	public void changeState(String email);

	public boolean emialIsActivate(String email);

	public void deleteAccount(String email);

	public boolean passwordIsRight(AccountDTO account);

	public boolean accountAndActivateCodeIsRight(AccountDTO accountDTO);

	public boolean activateCodeIsOverTime(AccountDTO accountDTO);

	public String getAddress();

	public void sendEmailToChangePassword(AccountDTO accountDTO);

	public void changePassword(AccountDTO accountDTO);
	
	//封装到showPictureOfAlbum的UserDTO
	public List<UserDTO> findUserDTOByAlbumId(Long userId, Long albumId);
	
	//根据email查询用户
	public User findUserByEmail(String email);
	
	//模糊查询用户
	public List<UserDTO> researchUser(Long myUserId, String likeStr);
	
	

}
