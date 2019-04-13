package com.example.demo.account.entity;

public class UserDTO
{
	private Long userId;
	private String userName;
	private String userPicture;
	private int collectionNumber;
	private int focusOnNumber;
	private int fansNumber;
	private int albumNumber;
	//是否已关注 0_否   1_是
	private int isFocusOn;
	private int isMyUser;
	
	
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
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
	public int getCollectionNumber()
	{
		return collectionNumber;
	}
	public void setCollectionNumber(int collectionNumber)
	{
		this.collectionNumber = collectionNumber;
	}
	public int getFansNumber()
	{
		return fansNumber;
	}
	public void setFansNumber(int fansNumber)
	{
		this.fansNumber = fansNumber;
	}
	public int getAlbumNumber()
	{
		return albumNumber;
	}
	public void setAlbumNumber(int albumNumber)
	{
		this.albumNumber = albumNumber;
	}
	
	public int getIsFocusOn()
	{
		return isFocusOn;
	}
	public void setIsFocusOn(int isFocusOn)
	{
		this.isFocusOn = isFocusOn;
	}
	
	
	public int getIsMyUser()
	{
		return isMyUser;
	}
	public void setIsMyUser(int isMyUser)
	{
		this.isMyUser = isMyUser;
	}
	public int getFocusOnNumber()
	{
		return focusOnNumber;
	}
	public void setFocusOnNumber(int focusOnNumber)
	{
		this.focusOnNumber = focusOnNumber;
	}
	@Override
	public String toString()
	{
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", userPicture=" + userPicture
				+ ", collectionNumber=" + collectionNumber + ", focusOnNumber=" + focusOnNumber + ", fansNumber="
				+ fansNumber + ", albumNumber=" + albumNumber + ", isFocusOn=" + isFocusOn + ", isMyUser=" + isMyUser
				+ "]";
	}
}
