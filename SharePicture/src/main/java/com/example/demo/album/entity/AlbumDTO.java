package com.example.demo.album.entity;

public class AlbumDTO
{
	private Long id;
	// 标题
	private String albumTitle;
	// 类别
	private String albumClassification;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAlbumTitle()
	{
		return albumTitle;
	}
	
	public String getAlbumClassification()
	{
		return albumClassification;
	}
	
	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}
	
	public void setAlbumClassification(String albumClassification)
	{
		this.albumClassification = albumClassification;
	}

	@Override
	public String toString()
	{
		return "AlbumDTO [albumTitle=" + albumTitle + ", albumClassification=" + albumClassification + "]";
	}	

}
