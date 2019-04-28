package com.example.demo.communication.entity;

public class PrivateLetterDTO
{
	private Long id;
	private Long senderId;
	private Long receiverId;
	private int  isReaded;
	private String privateLetterWord;
	private String senderName;
	private String senderPicture;
	private String receiverPicture;
	public Long getId()
	{
		return id;
	}
	public Long getSenderId()
	{
		return senderId;
	}
	public Long getReceiverId()
	{
		return receiverId;
	}
	public int getIsReaded()
	{
		return isReaded;
	}
	public String getPrivateLetterWord()
	{
		return privateLetterWord;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setSenderId(Long senderId)
	{
		this.senderId = senderId;
	}
	public void setReceiverId(Long receiverId)
	{
		this.receiverId = receiverId;
	}
	public void setIsReaded(int isReaded)
	{
		this.isReaded = isReaded;
	}
	public void setPrivateLetterWord(String privateLetterWord)
	{
		this.privateLetterWord = privateLetterWord;
	}
	public String getSenderName()
	{
		return senderName;
	}
	public void setSenderName(String senderName)
	{
		this.senderName = senderName;
	}
	public String getSenderPicture()
	{
		return senderPicture;
	}
	public void setSenderPicture(String senderPicture)
	{
		this.senderPicture = senderPicture;
	}
	public String getReceiverPicture()
	{
		return receiverPicture;
	}
	public void setReceiverPicture(String receiverPicture)
	{
		this.receiverPicture = receiverPicture;
	}
	@Override
	public String toString()
	{
		return "PrivateLetterDTO [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", isReaded="
				+ isReaded + ", privateLetterWord=" + privateLetterWord + ", senderName=" + senderName
				+ ", senderPicture=" + senderPicture + ", receiverPicture=" + receiverPicture + "]";
	}
}
