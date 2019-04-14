package com.example.demo.account.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.AccountDTO;
import com.example.demo.account.entity.Check;
import com.example.demo.account.entity.State;
import com.example.demo.account.entity.User;
import com.example.demo.account.entity.UserDTO;
import com.example.demo.account.repository.CheckRepository;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.Album;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.repository.FocusOnAlbumRepository;
import com.example.demo.common.utils.Md5Util;
import com.example.demo.common.utils.SendEmailUtil;
import com.example.demo.common.utils.UuidUtil;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.personalCenter.repository.FansRepository;
import com.example.demo.picture.repository.PictureRepository;

@Service
@Transactional
public class AccountService implements IAccountService
{
	@Autowired
	private CheckRepository checkRepository;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private FocusOnAlbumRepository focusOnAlbumRepository;
    @Autowired
    private FansRepository fansRepository;
    @Autowired
    private PictureRepository pictureRepository;
	/**
	 * 保存用户，将密码转为md5摘要
	 */
	@Override
	public void saveUser(User user)
	{
		String Md5PassWord = Md5Util.getMd5(user.getPassword());
		user.setPassword(Md5PassWord);
		userRepository.save(user);
	}

	/**
	 * 账号是否已存在数据库
	 */
	@Override
	public boolean emialIsExist(String email)
	{
		User user = userRepository.findByEmial(email);
		if (user == null)
		{
			return false;
		}
		return true;
	}

	/**
	 * 验证账号是否已经激活
	 */
	public boolean emialIsActivate(String email)
	{
		User user = userRepository.findByEmial(email);
		if (user.getState() == State.YES)
		{
			return true;
		}
		return false;
	}

	/**
	 * 修改账号的为激活状态
	 * 
	 * @param email
	 */
	@Override
	public void changeState(String email)
	{
		User user = userRepository.findByEmial(email);
		userRepository.changeState(user.getId());
	}

	/**
	 * 保存账号，发送激活邮件
	 * 
	 * @param account
	 */
	@Override
	public void saveAccountAndSendEmial(AccountDTO account)
	{
		User user = new User();
		BeanUtils.copyProperties(account, user);
		saveUser(user);
		user = userRepository.findByEmial(user.getEmail());
		Check check = new Check();
		String uuidCode = UuidUtil.getUUID();
		check.setActivationCode(uuidCode);
		// 当前时间加30分钟
		check.setDeadline(System.currentTimeMillis() + 30 * 60 * 1000);
		check.setUser(user);
		checkRepository.save(check);
		String subject = "图片共享网站设计与实现-注册激活";
		StringBuffer content = new StringBuffer().append("<html><body><h3>你好，请点击以下链接激活账号，30分钟内有效</h3>")
				.append("\n<a style=\"text-decoration:none\"").append("href=\"").append(getAddress()).append("account/")
				.append("activate?activationCode=").append(uuidCode).append("&email=").append(user.getEmail())
				.append("\"").append("<h3>账号激活_快来点我啊</h3></a></body></html>");
		SendEmailUtil.sendHtmlMail(user.getEmail(), subject, content.toString());
	}

	/**
	 * 删除账号
	 * 
	 * @param email
	 */
	public void deleteAccount(String email)
	{
		User user = userRepository.findByEmial(email);
		checkRepository.deleteCheckByUserId(user.getId());
		userRepository.deleteById(user.getId());
	}

	/**
	 * 密码是否正确
	 * 
	 * @return
	 */
	public boolean passwordIsRight(AccountDTO account)
	{
		User user = userRepository.findByEmial(account.getEmail());
		String password = Md5Util.getMd5(account.getPassword());
		if (password.equals(user.getPassword()))
		{
			return true;
		}
		return false;
	}

	/**
	 * 账号和激活码是否正确
	 */
	public boolean accountAndActivateCodeIsRight(AccountDTO accountDTO)
	{
		String activationCode = accountDTO.getActivationCode();
		String email = accountDTO.getEmail();
		Check check = checkRepository.findCheckByActivateCode(activationCode);
		if (check != null)
		{
			if (check.getUser().getEmail().equals(email))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证码是否超时
	 */
	public boolean activateCodeIsOverTime(AccountDTO accountDTO)
	{
		String activationCode = accountDTO.getActivationCode();
		Long deadLine = checkRepository.findCheckByActivateCode(activationCode).getDeadline();
		Long nowTime = System.currentTimeMillis();
		if (nowTime > deadLine)
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取ip和端口号
	 */
	public String getAddress()
	{
		String ipAddress = null;
		try
		{
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer().append("http://").append(ipAddress).append(":8080/");
		return sb.toString();
	}

	/**
	 * 向邮箱发送修改密码链接
	 */
	public void sendEmailToChangePassword(AccountDTO account)
	{
		String email = account.getEmail();
		User user = userRepository.findByEmial(email);
		Check check = new Check();
		String uuidCode = UuidUtil.getUUID();
		check.setActivationCode(uuidCode);
		// 当前时间加30分钟
		check.setDeadline(System.currentTimeMillis() + 30 * 60 * 1000);
		check.setUser(user);
		checkRepository.save(check);
		String subject = "图片共享网站设计与实现-修改密码";
		StringBuffer content = new StringBuffer().append("<html><body><h3>你好，请点击以下链接修改密码，30分钟内有效</h3>")
				.append("\n<a style=\"text-decoration:none\"").append("href=\"").append(getAddress()).append("account/")
				.append("toChangePasswordPage?activationCode=").append(uuidCode).append("&email=").append(user.getEmail())
				.append("\"").append("<h3>密码修改_快来点我啊</h3></a></body></html>");
		SendEmailUtil.sendHtmlMail(user.getEmail(), subject, content.toString());
	}

	/**
	 * 修改密码
	 */
	public void changePassword(AccountDTO accountDTO)
	{
		String Md5PassWord = Md5Util.getMd5(accountDTO.getPassword());
		String email = accountDTO.getEmail();
		userRepository.changePassword(Md5PassWord, email);
	}
	
	/*
	 * 封装到showPictureOfAlbum的UserDTO
	 * 1.该用户是否已关注
	 * 2.用户DTO中不能有自己
	 *
	 */
	public List<UserDTO> findUserDTOByAlbumId(Long userId, Long albumId)
	{
		List<UserDTO> userDTOs = null;
		if(albumId != null && albumId !=0)
		{
			Album album = albumRepository.findById(albumId).get();
			if(album != null)
			{
				List<Long> userIds = focusOnAlbumRepository.findFocusOnUserIdsByAlbumId(albumId);
				if(userIds != null && !userIds.isEmpty())
				{
					userDTOs = new ArrayList<UserDTO>();
					for(Long userIdTemp :userIds)
					{
						UserDTO userDTO = new UserDTO();
						int fansNumber = fansRepository.findFansNumberByUserId(userIdTemp);
						int findPictureNumber =pictureRepository.findPictureNumberByUserId(userIdTemp);
						User user = userRepository.findById(userIdTemp).get();
						userDTO.setUserName(user.getName());
						userDTO.setUserPicture(user.getUserPicture());
						userDTO.setFansNumber(fansNumber);
						userDTO.setCollectionNumber(findPictureNumber);
						userDTO.setUserId(userIdTemp);
						Fans fans = fansRepository.findByUserIdAndFansId(userIdTemp, userId);
						if(fans == null)
						{
							userDTO.setIsFocusOn(0);
						}
						else
						{
							userDTO.setIsFocusOn(1);
						}
						userDTOs.add(userDTO);
					}
					return userDTOs;
				}
			}
		}
		return userDTOs;
	}
	
	/*
	 * 根据email查询用户
	 */
    public User findUserByEmail(String email)
    {
    	return userRepository.findByEmial(email);
    }
	
}
