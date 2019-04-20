package com.example.demo.communication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_commentOfPicture")
public class CommentOfPicture
{
	private Long id;
	private Long userId;
	private Long pictureId;
	private String commentWord;
	
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
	public Long getPictureId()
	{
		return pictureId;
	}
	public String getCommentWord()
	{
		return commentWord;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public void setPictureId(Long pictureId)
	{
		this.pictureId = pictureId;
	}
	public void setCommentWord(String commentWord)
	{
		this.commentWord = commentWord;
	}
	@Override
	public String toString()
	{
		return "CommentOfPicture [id=" + id + ", userId=" + userId + ", pictureId=" + pictureId + ", commentWord="
				+ commentWord + "]";
	}
	
}
