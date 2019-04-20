package com.example.demo.communication.service;

public interface ICommentOfPictureService
{
	public boolean saveComment(Long myUserId, Long pictureId, String commentWord);
	
	//将图片的评论序列化
	public String commentsOfPictureTOJson(Long pictureId);
}
