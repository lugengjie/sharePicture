package com.example.demo.album.entity;

public class AlbumTesta
{
	private Long id;
	// 标题
	private String albumTitle;
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
	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}
	@Override
	public String toString()
	{
		return "AlbumTest [id=" + id + ", albumTitle=" + albumTitle + "]";
	}
	
}
