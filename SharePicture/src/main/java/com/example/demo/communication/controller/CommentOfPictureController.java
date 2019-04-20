package com.example.demo.communication.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.account.entity.UserDTO;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.communication.service.CommentOfPictureService;

@Controller
@RequestMapping(value = "/commentOfPicture")
public class CommentOfPictureController
{
	@Autowired
	CommentOfPictureService commentOfPictureService;
	
	@RequestMapping(value="/saveComment")
	public @ResponseBody String saveComment(HttpSession session,Long pictureId,String commentWord, Model model)
	{
		Long myUserId = (Long) session.getAttribute("userId");
		if(commentOfPictureService.saveComment(myUserId, pictureId, commentWord))
		{
			return "成功";
		}
		return "失败";
	}

}
