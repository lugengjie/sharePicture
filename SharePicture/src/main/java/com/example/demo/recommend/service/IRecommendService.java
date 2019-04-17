package com.example.demo.recommend.service;

import java.util.List;

import com.example.demo.picture.entity.PictureDTO;

public interface IRecommendService
{
	/*根据兴趣名和限制条数查询PictureDTO*/
	public List<PictureDTO> findPictureDTOByInterestNameAndLimitNumber(Long myUserId, String interestName, int limitNumber);
	
	/*封装发送到Recommend的PicturerDTO*/
	public List<PictureDTO> RecommendPageOfPictureDTO(Long myUserId);
	
}
