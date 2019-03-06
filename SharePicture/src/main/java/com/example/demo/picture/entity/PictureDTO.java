package com.example.demo.picture.entity;


public class PictureDTO
{
	// 标签
	private String pictureLabel;
	// 描述
	private String pictureDescribe;
	// 图片名称
	private String pictureName;
	// 所属相册
	private Long albumId;
	
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
}
