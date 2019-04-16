package com.example.demo.personalCenter.entity;

import java.util.Arrays;
import java.util.List;

public class UserSettingDTO
{
	private String  userName="";
	private String  userPicture;
	private String[] interestNames;
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserPicture()
	{
		return userPicture;
	}
	public void setUserPicture(String userPicture)
	{
		this.userPicture = userPicture;
	}
	
	
	
	public String[] getInterestNames()
	{
		return interestNames;
	}
	public void setInterestNames(String[] interestNames)
	{
		this.interestNames = interestNames;
	}
	@Override
	public String toString()
	{
		return "UserSettingDTO [userName=" + userName + ", userPicture=" + userPicture + ", interestNames="
				+ Arrays.toString(interestNames) + "]";
	}

}
