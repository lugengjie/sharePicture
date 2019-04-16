package com.example.demo.personalCenter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_interest")
public class Interest
{
	private Long id;
	private Long userId;
	private String interestName;
	
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
	
	 
	public String getInterestName()
	{
		return interestName;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public void setInterestName(String interestName)
	{
		this.interestName = interestName;
	}
	@Override
	public String toString()
	{
		return "Interest [id=" + id + ", userId=" + userId + ", interestName=" + interestName + "]";
	}
}
