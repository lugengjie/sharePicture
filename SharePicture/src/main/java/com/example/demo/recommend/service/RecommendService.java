package com.example.demo.recommend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.personalCenter.repository.InterestRepository;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.repository.LikePictureRepository;
import com.example.demo.picture.repository.PictureRepository;


@Service
@Transactional
public class RecommendService implements IRecommendService
{
	@Autowired
	PictureRepository pictureRepository;
	@Autowired
	LikePictureRepository likePictureRepository;
	@Autowired
	InterestRepository interestRepository;
	
	/*根据兴趣名和限制条数查询PictureDTO
	 * 1.排除自己的
	 */
	public List<PictureDTO> findPictureDTOByInterestNameAndLimitNumber(Long myUserId, String interestName, int limitNumber)
	{
		List<PictureDTO> pictureDTOs = null;
		Pageable pageable = PageRequest.of(0, limitNumber);
		List<Object> objectTemps = pictureRepository.findPictureDTOByInterestNameAndPageable(interestName, pageable);
		if(objectTemps!=null && !objectTemps.isEmpty())
		{
			pictureDTOs=new ArrayList<PictureDTO>();
			for(Object objectTemp:objectTemps)
			{
				Object[] pictureDTOTemp=(Object[])objectTemp;
				PictureDTO pictureDTO = new PictureDTO();
				pictureDTO.setPictureId((Long)pictureDTOTemp[0]);
				pictureDTO.setPictureDescribe((String)pictureDTOTemp[1]);
				pictureDTO.setPictureName((String)pictureDTOTemp[2]);
				pictureDTO.setLikeNumber((int)pictureDTOTemp[3]);
				pictureDTO.setCollectNumber((int)pictureDTOTemp[4]);
				pictureDTO.setPictureLabel((String)pictureDTOTemp[5]);
				pictureDTO.setAlbumId((Long)pictureDTOTemp[6]);
				pictureDTO.setAlbumName((String)pictureDTOTemp[7]);
				pictureDTO.setUserId((Long)pictureDTOTemp[8]);
				pictureDTO.setIsLike(0);
				if(pictureDTO.getUserId().equals(myUserId))
				{
					//该图片是用户自己的
					pictureDTO.setIsMine(1);
				}
				else
				{
					//该图片不是用户自己的
					pictureDTO.setIsMine(0);
				}
				pictureDTO.setUserName((String)pictureDTOTemp[9]);
				pictureDTO.setUserPicture((String)pictureDTOTemp[10]);
				if(!pictureDTO.getUserId().equals(myUserId))
				{
					pictureDTOs.add(pictureDTO);
				}
				
			}
			//pictureDTO中id在用户喜欢的图片id中，则将改pictureDTO的isLike属性设为1，否则设为0
			List<Long> likePictureIds = likePictureRepository.findLikePictureIdsByUserId(myUserId);
			if(likePictureIds !=null && !likePictureIds.isEmpty())
			{
				for(PictureDTO pictureDTO : pictureDTOs)
				{
					if(likePictureIds.contains(pictureDTO.getPictureId()))
					{
						pictureDTO.setIsLike(1);
					}
				}
			}
			
		}
		return pictureDTOs;
	}
	
	/**
	 * 根据图片类别名查询排行版
	 */
	public List<PictureDTO> findSortPictureByClassification(Long myUserId,String interestName)
	{
		List<PictureDTO> pictureDTOs = null;
		if(myUserId !=null)
		{		
			int limitNumber = 100;
			pictureDTOs = findPictureDTOByInterestNameAndLimitNumber(myUserId, interestName, limitNumber);	
		}
		return pictureDTOs;
	}
	
	
	/*封装发送到Recommend的PicturerDTO*/
	public List<PictureDTO> RecommendPageOfPictureDTO(Long myUserId)
	{
		List<PictureDTO> pictureDTOs = null;
		if(myUserId !=null)
		{
			List<String> interestNames = interestRepository.findInterestByUserId(myUserId);
			if(interestNames != null && !interestNames.isEmpty())
			{
				int limitNumber = 100/interestNames.size();
				pictureDTOs = new ArrayList<PictureDTO>();
				for(String interestName: interestNames)
				{
					List<PictureDTO> pictureDTOTemps = findPictureDTOByInterestNameAndLimitNumber(myUserId, interestName, limitNumber);
					if(pictureDTOTemps!=null && !pictureDTOTemps.isEmpty())
					{
						pictureDTOs.addAll(pictureDTOTemps);
					}
				}
				Collections.shuffle(pictureDTOs);
			}
			
		}
		return pictureDTOs;
	}
}
