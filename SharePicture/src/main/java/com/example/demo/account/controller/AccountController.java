package com.example.demo.account.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.service.AccountService;

@Controller
@RequestMapping(value = "/account")
public class AccountController
{
	@Autowired 
	private AccountService accountService;

	/*
	 * 注册流程
	 * 注册 邮箱是否存在 ？-》是，账号是否激活？     -》是，账号已被注册 
	 *              -》否，保存并发激活邮件
	 *              				-》否，删除已有账号，从新保存定发激活邮件
	 * @param accountDTO
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	public @ResponseBody String register(AccountDTO accountDTO)
	{
		if (accountDTO.getEmail().equals("") || accountDTO.getPassword().equals(""))
		{
			return "账号或密码不能为空";
		}
		String email = accountDTO.getEmail();
		if (accountService.emialIsExist(email))
		{
			if (accountService.emialIsActivate(email))
			{
				return "邮箱已被注册";
			} 
			else
			{
				accountService.deleteAccount(email);
				accountService.saveAccountAndSendEmial(accountDTO);
				return "激活链接已发送至邮箱，请尽快激活";
			}
		} 
		else
		{
			accountService.saveAccountAndSendEmial(accountDTO);
			return "激活链接已发送至邮箱，请尽快激活";
		}
	}

	/**
	 * 登录流程
	 *账号或密码是否为空-》否，账号是否存在? -》是，账号是否激活？-》是，密码是否正确?-》是，登录成功  
	 *		                      -》否，账号不存在              
	 *                                           -》否，请尽快激活
	 * 														  -》否，密码错误                                 
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value = "/login")
	public @ResponseBody String Login(HttpSession session, AccountDTO accountDTO)
	{
		if (accountDTO.getEmail().equals("") || accountDTO.getPassword().equals(""))
		{
			return "账号或密码不能为空";
		}
		String email = accountDTO.getEmail();
		if (accountService.emialIsExist(email))
		{
			if (accountService.emialIsActivate(email))
			{
				if (accountService.passwordIsRight(accountDTO))
				{
					session.setAttribute("isAllowPass", true);
					return "登录成功";
				} 
				else
				{
					return "密码错误";
				}
			}
			else
			{
				return "账号未激活";
			}
		}
		else
		{
			return "账号不存在";
		}
	}

	/**
	 * 激活账号
	 * 激活码和账号是否正确？-》是，激活码是否超时？-》是，激活码超时  
	 *                                -》否，激活成功
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value = "/activate")
	public  String activate(AccountDTO accountDTO,Model model)
	{
		if (accountService.accountAndActivateCodeIsRight(accountDTO))
		{
			if (accountService.activateCodeIsOverTime(accountDTO))
			{
				model.addAttribute("activateInfomation", "账号激活失败(激活码超时)");
			}
			String email = accountDTO.getEmail();
			accountService.changeState(email);
			model.addAttribute("activateInfomation", "账号激活成功");
		}
		else
		{
			model.addAttribute("activateInfomation", "攻击行为已被记录");
		}
		return "activate";
	}

	/**
	 * 填写邮箱以修改密码
	 * 
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value = "/writeEmail")
	public @ResponseBody String sendEmailToChangePassword(AccountDTO accountDTO)
	{
		if (accountDTO.getEmail().equals(""))
		{
			return "账号不能为空";
		}
		String email = accountDTO.getEmail();
		if (accountService.emialIsExist(email))
		{
			accountService.sendEmailToChangePassword(accountDTO);
			return "修改密码链接已发送至邮箱";
		}
		return "账号不存在";
	}
	
	/**
	 * 跳转到修改密码页面
	 */
	@RequestMapping(value = "/toChangePasswordPage")
	public String tochangePasswordPage()
	{
		return "changePassword";
	}
	
	/**
	 * 修改密码 
	 * 输入邮箱-》发修改链接到邮箱-》修改密码
	 * 
	 * @param accountDTO
	 * @return
	 */
	@RequestMapping(value = "/changePassword")
	public @ResponseBody String changePassword(AccountDTO accountDTO)
	{
		if (accountDTO.getPassword().equals(""))
		{
			return "密码不能为空";
		}
		if (accountService.accountAndActivateCodeIsRight(accountDTO))
		{
			if (accountService.activateCodeIsOverTime(accountDTO))
			{
				return "修改密码失败(链接超时)";
			}
			accountService.changePassword(accountDTO);
			return "修改密码成功";
		}
		return "攻击行为已被记录";
	}

}
