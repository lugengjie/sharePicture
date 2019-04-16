package com.example.demo.personalCenter.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.personalCenter.entity.Interest;
import com.example.demo.personalCenter.entity.UserSettingDTO;

public interface IInterestService
{
	/*修改用户设置*/
	public boolean userSetting(MultipartFile multipartFile, Long myUserId, UserSettingDTO userSettingDTO);
}
