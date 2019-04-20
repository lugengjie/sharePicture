package com.example.demo.communication.entity;

public class CommentOfPictureDTO
{
	private Long id;
	private Long userId;
	private Long pictureId;
	private String commentWord;
	private String userPicture;
	private String userName;
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
	public String getCommentWord()
	{
		return commentWord;
	}
	public void setCommentWord(String commentWord)
	{
		this.commentWord = commentWord;
	}
	public String getUserPicture()
	{
		return userPicture;
	}
	public void setUserPicture(String userPicture)
	{
		this.userPicture = userPicture;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	@Override
	public String toString()
	{
		return "CommentOfPictureDTO [id=" + id + ", userId=" + userId + ", pictureId=" + pictureId + ", commentWord="
				+ commentWord + ", userPicture=" + userPicture + ", userName=" + userName + "]";
	}
}
