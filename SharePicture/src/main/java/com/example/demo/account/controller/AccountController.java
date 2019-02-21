package com.example.demo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.service.AccountService;

@Controller
@RequestMapping(value="/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	/*
	 * 注册
	 * 邮箱是否存在 ？-》是，账号是否激活？-》是，账号已被注册
	 *         -》否，请尽快激活
	 *                       -》否，请尽快激活
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value="/register")
	public @ResponseBody String register(AccountDTO accountDTO) {
		String email=accountDTO.getEmail();
		if(accountService.emialIsExist(email)) {
			if(accountService.emialIsActivate(email)) {
				return "邮箱已被注册";
			}else {
				accountService.deleteAccount(email);
				accountService.saveAccountAndSendEmial(accountDTO);
				return  "请尽快激活";
			}
		}else {
			accountService.saveAccountAndSendEmial(accountDTO);
			return "请尽快激活";
		}
	}
	/**
	 * 登录
	 * 填写账号密码-》邮箱是否存在?-》是，账号是否激活？-》是，密码是否正确?-》是，登录成功           
		                 -》否，账号不存在
                                       -》否，请尽快激活
                                                     -》否，密码错误
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value="/login")
	public @ResponseBody String Login(AccountDTO accountDTO) {
		if(accountDTO.getEmail()==null||accountDTO.getPassword()==null) {
			return "邮箱或密码不能为空";
		}
		String email=accountDTO.getEmail();
		if(accountService.emialIsExist(email)) {
			if(accountService.emialIsActivate(email)){
				if(accountService.passwordIsRight(accountDTO)) {
					return "登录成功";
				}else {
					return "密码错误";
				}	
			}else {
				return "账号未激活";
			}
		}else {
			return "账号不存在";
		}
	}
	/**
	 * 激活账号
	 * 激活码和账号是否正确？-》是，激活码是否超时？-》是，激活码超时
	 * 			-》否
	 * 							-》否，激活成功
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value="/activate")
	public @ResponseBody String activate(AccountDTO accountDTO){
		if(accountService.accountAndActivateCodeIsRight(accountDTO)) {
			if(accountService.activateCodeIsOverTime(accountDTO)) {
				return "验证码超时";
			}
			String email=accountDTO.getEmail();
			accountService.changeState(email);
			return "验证成功";
		}
		return "黑客";
	}
	/**
	 * 填写邮箱
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value="/writeEmail")
	public @ResponseBody String sendEmailToChangePassword(AccountDTO accountDTO){
		if(accountDTO.getEmail()==null) {
			return "邮箱不能为空";
		}
		String email=accountDTO.getEmail();
		if(accountService.emialIsExist(email)) {
			accountService.sendEmailToChangePassword(accountDTO);
			return "修改密码链接已发送至邮箱";
		}
		return "邮箱不存在";
	}
	/**
	 * 修改密码
	 * 输入邮箱-》发修改链接到邮箱-》修改密码
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value="/changePassword")
	public @ResponseBody String changePassword(AccountDTO accountDTO) {
		if(accountDTO.getPassword()==null) {
			return "密码不能为空";
		}
		if(accountService.accountAndActivateCodeIsRight(accountDTO)) {
			if(accountService.activateCodeIsOverTime(accountDTO)) {
				return "链接超时";
			}
			accountService.changePassword(accountDTO);
			return "修改密码成功";
		}
		return "黑客";
	}
	
}
