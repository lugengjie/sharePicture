package com.example.demo.album.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_focusOnAlbum")
public class FocusOnAlbum
{
	private Long id;
	private Long userId;
	private Long albumId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}
	public Long getUserId()
	{
		return userId;
	}
	public Long getAlbumId()
	{
		return albumId;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public void setAlbumId(Long albumId)
	{
		this.albumId = albumId;
	}
	@Override
	public String toString()
	{
		return "FocusOnAlbum [id=" + id + ", userId=" + userId + ", albumId=" + albumId + "]";
	}	
}
