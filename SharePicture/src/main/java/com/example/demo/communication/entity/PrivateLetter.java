package com.example.demo.communication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_privateLetter")
public class PrivateLetter
{
	private Long id;
	private Long senderId;
	private Long receiverId;
	private int  isReaded = 0;
	private String privateLetterWord;
	
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
	public Long getSenderId()
	{
		return senderId;
	}
	public void setSenderId(Long senderId)
	{
		this.senderId = senderId;
	}
	public Long getReceiverId()
	{
		return receiverId;
	}
	public void setReceiverId(Long receiverId)
	{
		this.receiverId = receiverId;
	}
	public int getIsReaded()
	{
		return isReaded;
	}
	public void setIsReaded(int isReaded)
	{
		this.isReaded = isReaded;
	}
	public String getPrivateLetterWord()
	{
		return privateLetterWord;
	}
	public void setPrivateLetterWord(String privateLetterWord)
	{
		this.privateLetterWord = privateLetterWord;
	}
	@Override
	public String toString()
	{
		return "PrivateLetter [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", isReaded="
				+ isReaded + ", privateLetterWord=" + privateLetterWord + "]";
	}
}
