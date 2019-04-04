package com.example.demo.picture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_likePicture")
public class LikePicture
{
	Long id;
	Long userId;
	Long pictureId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public Long getPictureId()
	{
		return pictureId;
	}
	public void setPictureId(Long pictureId)
	{
		this.pictureId = pictureId;
	}
	@Override
	public String toString()
	{
		return "likePicture [id=" + id + ", userId=" + userId + ", pictureId=" + pictureId + "]";
	}
}
