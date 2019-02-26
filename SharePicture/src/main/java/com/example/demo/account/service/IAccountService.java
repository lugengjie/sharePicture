package com.example.demo.account.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.entity.Check;
import com.example.demo.account.entity.User;

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
}
