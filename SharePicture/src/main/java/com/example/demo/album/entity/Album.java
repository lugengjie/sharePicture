package com.example.demo.album.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_album")
public class Album
{
	private Long id;
	// 标题
	private String albumTitle;
	// 描述
	private String albumDescribe;
	// 类别
	private String albumClassification;
	// 图片数
	private int pictureNumber;
	// 用户Id
	private Long userId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}
	
	public String getAlbumTitle()
	{
		return albumTitle;
	}
	
	public String getAlbumDescribe()
	{
		return albumDescribe;
	}
	
	public String getAlbumClassification()
	{
		return albumClassification;
	}
	
	public int getPictureNumber()
	{
		return pictureNumber;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}
	
	public void setAlbumDescribe(String albumDescribe)
	{
		this.albumDescribe = albumDescribe;
	}
	
	public void setAlbumClassification(String albumClassification)
	{
		this.albumClassification = albumClassification;
	}	
	
	public void setPictureNumber(int pictureNumber)
	{
		this.pictureNumber = pictureNumber;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	@Override
	public String toString()
	{
		return "Album [id=" + id + ", albumTitle=" + albumTitle + ", albumDescribe=" + albumDescribe
				+ ", albumClassification=" + albumClassification + ", pictureNumber="
				+ pictureNumber + ", userId=" + userId + "]";
	}
	
}
