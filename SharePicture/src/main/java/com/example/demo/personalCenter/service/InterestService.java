package com.example.demo.personalCenter.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.common.utils.FileUploadUtil;
import com.example.demo.personalCenter.entity.Interest;
import com.example.demo.personalCenter.entity.UserSettingDTO;
import com.example.demo.personalCenter.repository.InterestRepository;

@Service
@Transactional
public class InterestService implements IInterestService
{
	@Autowired
	InterestRepository interestRepository;
	@Autowired
	UserRepository userRepository;
	@Value("${web.upload-path}")
	String localAbsolutePath;
	
	/*修改用户设置*/
	public boolean userSetting(MultipartFile multipartFile, Long myUserId, UserSettingDTO userSettingDTO)
	{
		if(myUserId != null) 
		{
			User user = userRepository.findById(myUserId).get();
			String userName = userSettingDTO.getUserName();
			String fileName=FileUploadUtil.upLoad(multipartFile, localAbsolutePath);
			if(fileName!=null)
			{
				user.setUserPicture(fileName);
			}
			if(userName != null&& !userName.equals(""))
			{
				user.setName(userName);
			}
			userRepository.save(user);
			String[] interestNames = userSettingDTO.getInterestNames();
			interestRepository.deleteInterestByUserId(myUserId);
			if(interestNames!=null && interestNames.length!=0)
			{
				for(int i=0;i<interestNames.length;i++)
				{
					Interest interest = new Interest();
					interest.setInterestName(interestNames[i]);
					interest.setUserId(myUserId);
					interestRepository.save(interest);
				}
				
			}
			return true;
		}
		return false;
    }
}
