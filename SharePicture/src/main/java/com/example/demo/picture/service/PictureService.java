package com.example.demo.picture.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.account.entity.User;
import com.example.demo.account.repository.UserRepository;
import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumDTO;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.album.service.AlbumService;
import com.example.demo.common.utils.FileUploadUtil;
import com.example.demo.picture.entity.LikePicture;
import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;
import com.example.demo.picture.repository.LikePictureRepository;
import com.example.demo.picture.repository.PictureRepository;

@Service
@Transactional
public class PictureService implements IPictureService
{
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LikePictureRepository likePictureRepository;
	
	@Value("${web.upload-path}")
	String localAbsolutePath;
	
	/**
	 * 保存图片路径
	 */
	public void savePicture(PictureDTO pictureDto)
	{
		Picture picture=new Picture();
		BeanUtils.copyProperties(pictureDto, picture);
		picture.setId(null);
		pictureRepository.save(picture);		
	}
	
	
	
	/**
	 * 上传图片
	 * 1.判断文件是否为空
	 * 2.判断文件是否为图片
	 * 3.上传文件到本地
	 * 4.处理图片与相册的关系
	 */
	public boolean uploadPicture(MultipartFile multipartFile,PictureDTO picture)
	{
		if(multipartFile==null)
		{	
			return false;
		}
		if(!FileUploadUtil.isImage(multipartFile))
		{
			return false;
		}
		String fileName=FileUploadUtil.upLoad(multipartFile, localAbsolutePath);
		if(fileName==null)
		{
			return false;
		}
		picture.setPictureName(fileName);
		savePicture(picture);
		return true;
	}
	
	/**
	 * 展示相册中所有图片
	 */
	public AlbumDTO showAllPictureOfAlbum(AlbumDTO albumDTO)
	{
		Long albumId=albumDTO.getId();
		AlbumDTO temp=new AlbumDTO();
		BeanUtils.copyProperties(albumDTO, temp);
		if(albumId>0) 
		{
			List<Picture> pictures=pictureRepository.findPictureByAlbumId(albumId);
			if (pictures != null && !pictures.isEmpty())
			{
				Collections.reverse(pictures);
				for(Picture picture:pictures)
				{
					temp.getPictureNames().add(picture.getPictureName());
				}
			}
		}
		return temp;	
	}
	
	/**
	 * 图片轮播
	 * 1.根据图片id查询图片所在的相册，相册所在用户
	 * 2.把请求中的图片所在相册的位置的前的不超过8张图片（包括该图片）加该图片后的所有图片放在PictureDTO中
	 * 3.将所有信息放在AlbumDTO中返回
	 */
	public AlbumDTO pictureCarousel(Long pictureId)
	{
		Picture picture=pictureRepository.findById(pictureId).get();	
		AlbumDTO albumDTO=new AlbumDTO();
		if(picture != null)
		{		
			albumDTO.setCoverPictureName(picture.getPictureName());
			albumDTO.setId(picture.getAlbumId());
			albumDTO.setMainPictureDescribe(picture.getPictureDescribe());
			Album album = albumRepository.findAlbumByAlbumId(picture.getAlbumId());		
			albumDTO.setAlbumTitle(album.getAlbumTitle());
			User user = userRepository.findUserByUserId(album.getUserId());		
			albumDTO.setUserName(user.getName());
			albumDTO.setUserPicture(user.getUserPicture());
			albumDTO.setUserId(user.getId());
			List<Picture> pictures=pictureRepository.findPictureByAlbumId(picture.getAlbumId());
			Collections.reverse(pictures);
			int index = pictures.indexOf(picture);	
			int i=index;
			for(;i>=0;i--)
			{		
				if(index-i>=8)
				{
					break;
				}
				PictureDTO pictureDTO = new PictureDTO();
				BeanUtils.copyProperties(pictures.get(i), pictureDTO);
				pictureDTO.setPictureId(pictures.get(i).getId());
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
			Collections.reverse(albumDTO.getPictureDTOs());
			albumDTO.setPictureIndex(index-i-1);
			for(int j=index+1;j<pictures.size();j++)
			{
				PictureDTO pictureDTO = new PictureDTO();
				BeanUtils.copyProperties(pictures.get(j), pictureDTO);
				pictureDTO.setPictureId(pictures.get(j).getId());
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
			
		}
		return albumDTO;
	}
	
	/**
	 * 喜欢图片
	 * 检查该用户是否已喜欢该图片，是则结束
	 * 将喜欢的图片id和用户id放入喜欢图片表中，并将图片表对应图片的likeNumber+1
	 */
	public void likePicture(Long pictureId,String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			Picture picture=pictureRepository.findById(pictureId).get();
			if(picture !=null)
			{
				LikePicture likePictureTemp = likePictureRepository.findLikePictureByUserIdAndPictureId(user.getId(), pictureId);
				if (likePictureTemp != null)
				{
					return ;
				}
				LikePicture likePicture = new LikePicture();
				int likeNumber = picture.getLikeNumber() + 1;
				picture.setLikeNumber(likeNumber);
				likePicture.setPictureId(pictureId);
				likePicture.setUserId(user.getId());
				likePictureRepository.save(likePicture);
			}
		}
	}
		
	/**
	 * 取消喜欢图片
	 * 检查该用户是否已喜欢该图片，否则结束
	 * 根据图片id和用户id删除喜欢图片表内的数据
	 */
	public void cancelLikePicture(Long pictureId,String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			LikePicture likePictureTemp = likePictureRepository.findLikePictureByUserIdAndPictureId(user.getId(), pictureId);
			if (likePictureTemp == null)
			{
				return ;
			}
			Picture picture=pictureRepository.findById(pictureId).get();
			if(picture !=null)
			{
				Long userId = user.getId();
				int likeNumber = picture.getLikeNumber() - 1;
				pictureRepository.cancelLikePicture(pictureId, likeNumber);
				likePictureRepository.deleteByPictureAndUser(pictureId, userId);
			}
		}
	}
		
	/**
	 * 收藏图片
	 * 先检查该用户是否拥有该相册
	 * 将收藏图片存入数据库，并将原来的图片的collecteNumber+1
	 */
	public void collectPicture(PictureDTO pictureDTO, String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			Album album = albumRepository.findAlbumByAlbumIdAndUserId(pictureDTO.getAlbumId(), user.getId());
			if(album != null)
			{
				Long pictureId = pictureDTO.getPictureId();
				Picture picture=pictureRepository.findById(pictureId).get();
				int collectNumber = picture.getCollectNumber();
				picture.setCollectNumber(collectNumber+1);
				pictureRepository.save(picture);
				savePicture(pictureDTO);
			}
			
		}
	}

}
