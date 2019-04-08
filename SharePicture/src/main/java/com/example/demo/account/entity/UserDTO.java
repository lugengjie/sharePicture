package com.example.demo.account.entity;

public class UserDTO
{
	private String userName;
	private String userPicture;
	private int collectionNumber;
	private int fansNumber;
	private int albumNumber;
	
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
	
	@Override
	public String toString()
	{
		return "UserDTO [userName=" + userName + ", userPicture=" + userPicture + ", collectionNumber="
				+ collectionNumber + ", fansNumber=" + fansNumber + ", albumNumber=" + albumNumber + "]";
	}
}
