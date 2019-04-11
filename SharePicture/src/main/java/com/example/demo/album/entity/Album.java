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
	
	// 用户Id
	private Long userId;
	// 被关注数
	private int focusNumber;
	
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

	
	public Long getUserId()
	{
		return userId;
	}
	
	
	public int getFocusNumber()
	{
		return focusNumber;
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
	

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public void setFocusNumber(int focusNumber)
	{
		this.focusNumber = focusNumber;
	}

	@Override
	public String toString()
	{
		return "Album [id=" + id + ", albumTitle=" + albumTitle + ", albumDescribe=" + albumDescribe
				+ ", albumClassification=" + albumClassification + ", userId="
				+ userId + ", focusNumber=" + focusNumber + "]";
	}
}
