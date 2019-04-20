package com.example.demo.communication.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.communication.entity.CommentOfPicture;
import com.example.demo.communication.entity.CommentOfPictureDTO;
import com.example.demo.communication.repository.CommentOfPictureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CommentOfPictureService implements ICommentOfPictureService
{

	@Autowired
	CommentOfPictureRepository commentOfPictureRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public boolean saveComment(Long myUserId, Long pictureId, String commentWord)
	{
		if(myUserId != null)
		{
			if(pictureId != null && commentWord!=null && !commentWord.equals(""))
			{
				CommentOfPicture commentOfPicture = new CommentOfPicture();
				commentOfPicture.setUserId(myUserId);
				commentOfPicture.setCommentWord(commentWord);
				commentOfPicture.setPictureId(pictureId);
				commentOfPictureRepository.save(commentOfPicture);
				return true;
			}
		}
		return false;
	}
	
	public List<CommentOfPictureDTO> CommentOfPictureToDTO(List<CommentOfPicture> commentOfPictures)
	{
		List<CommentOfPictureDTO> commentOfPictureDTOs=new ArrayList<CommentOfPictureDTO>();
		for(CommentOfPicture temp:commentOfPictures)
		{
			CommentOfPictureDTO commentOfPictureDTO = new CommentOfPictureDTO();
			BeanUtils.copyProperties(temp, commentOfPictureDTO);
			User user = userRepository.findById(temp.getUserId()).get();
			commentOfPictureDTO.setUserName(user.getName());
			commentOfPictureDTO.setUserPicture(user.getUserPicture());
			commentOfPictureDTOs.add(commentOfPictureDTO);
		}
		return commentOfPictureDTOs;

	}
	public String commentsOfPictureTOJson(Long pictureId)
	{
		String commentsOfPicture =null;
		if(pictureId!=null)
		{
			List<CommentOfPictureDTO> commentsOfPictureTemp = CommentOfPictureToDTO(commentOfPictureRepository.findCommentsByPictureId(pictureId));
			if(commentsOfPictureTemp!=null && !commentsOfPictureTemp.isEmpty())
			{
				ObjectMapper mapper = new ObjectMapper();
				try
				{
					commentsOfPicture = mapper.writeValueAsString(commentsOfPictureTemp);
				} catch (JsonProcessingException e)
				{
					e.printStackTrace();
				}  
			}
		}
		return commentsOfPicture;
	}

}
