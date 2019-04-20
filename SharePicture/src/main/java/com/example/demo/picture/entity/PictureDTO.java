package com.example.demo.picture.entity;


public class PictureDTO
{	
	//图片Id
	private Long pictureId;
	// 标签
	private String pictureLabel="";
	// 描述
	private String pictureDescribe="";
	// 图片名称
	private String pictureName="";
	//被喜欢数
	private int likeNumber = 0;
	//被收藏数
	private int collectNumber = 0;
	// 所属相册
	private Long albumId;
	//相册名
	private String albumName;
	//用户名
	private String userName;
	//用户Id
	private Long userId;
	//用户头像
	private String userPicture;
	//权限 1_是  0_不是
	private int isMine;
	//是否喜欢 1_是  0_不是
	private int isLike;
	private String commentsOfPicture;
	
	public Long getPictureId()
	{
		return pictureId;
	}
	public void setPictureId(Long pictureId)
	{
		this.pictureId = pictureId;
	}
	public String getPictureLabel()
	{
		return pictureLabel;
	}
	public void setPictureLabel(String pictureLabel)
	{
		this.pictureLabel = pictureLabel;
	}
	public String getPictureDescribe()
	{
		return pictureDescribe;
	}
	public void setPictureDescribe(String pictureDescribe)
	{
		this.pictureDescribe = pictureDescribe;
	}
	public String getPictureName()
	{
		return pictureName;
	}
	public void setPictureName(String pictureName)
	{
		this.pictureName = pictureName;
	}
	public Long getAlbumId()
	{
		return albumId;
	}
	public void setAlbumId(Long albumId)
	{
		this.albumId = albumId;
	}
	
	
	public String getAlbumName()
	{
		return albumName;
	}
	public void setAlbumName(String albumName)
	{
		this.albumName = albumName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public String getUserPicture()
	{
		return userPicture;
	}
	public void setUserPicture(String userPicture)
	{
		this.userPicture = userPicture;
	}
	
	public int getLikeNumber()
	{
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber)
	{
		this.likeNumber = likeNumber;
	}
	public int getCollectNumber()
	{
		return collectNumber;
	}
	public void setCollectNumber(int collectNumber)
	{
		this.collectNumber = collectNumber;
	}
	
	public int getIsMine()
	{
		return isMine;
	}
	public void setIsMine(int isMine)
	{
		this.isMine = isMine;
	}
	
	
	public int getIsLike()
	{
		return isLike;
	}
	public void setIsLike(int isLike)
	{
		this.isLike = isLike;
	}
	
	public String getCommentsOfPicture()
	{
		return commentsOfPicture;
	}
	public void setCommentsOfPicture(String commentsOfPicture)
	{
		this.commentsOfPicture = commentsOfPicture;
	}
	@Override
	public String toString()
	{
		return "PictureDTO [pictureId=" + pictureId + ", pictureLabel=" + pictureLabel + ", pictureDescribe="
				+ pictureDescribe + ", pictureName=" + pictureName + ", likeNumber=" + likeNumber + ", collectNumber="
				+ collectNumber + ", albumId=" + albumId + ", albumName=" + albumName + ", userName=" + userName
				+ ", userId=" + userId + ", userPicture=" + userPicture + ", isMine=" + isMine + ", isLike=" + isLike
				+ ", commentsOfPicture=" + commentsOfPicture + "]";
	}
	
	
}
