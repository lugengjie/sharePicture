package com.example.demo.personalCenter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_fans")
public class Fans
{
	private Long id;
	private Long userId;
	private Long fansId;
	
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
	public Long getFansId()
	{
		return fansId;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public void setFansId(Long fansId)
	{
		this.fansId = fansId;
	}
	@Override
	public String toString()
	{
		return "Fans [id=" + id + ", userId=" + userId + ", fansId=" + fansId + "]";
	}
	
}
