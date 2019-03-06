package com.example.demo.album.entity;

public class AlbumDTO
{
	// 标题
	private String albumTitle;
	// 类别
	private String albumClassification;
	
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
