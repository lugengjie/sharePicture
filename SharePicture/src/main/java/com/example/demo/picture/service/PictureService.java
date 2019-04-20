package com.example.demo.picture.service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
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
import com.example.demo.communication.service.CommentOfPictureService;
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
	private CommentOfPictureService commentOfPictureService;
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
	public AlbumDTO pictureCarousel(Long pictureId,Long myUserId)
	{
		if(myUserId == null)
		{
			return null;
		}
		Picture picture=pictureRepository.findById(pictureId).get();	
		AlbumDTO albumDTO=new AlbumDTO();
		if(picture != null)
		{		
			albumDTO.setRealPictureId(pictureId);
			User tourister = userRepository.findById(myUserId).get();
			albumDTO.setTouristId(tourister.getId());
			albumDTO.setTouristName(tourister.getName());
			albumDTO.setTouristPicture(tourister.getUserPicture());
			albumDTO.setCoverPictureName(picture.getPictureName());
			albumDTO.setId(picture.getAlbumId());
			albumDTO.setMainPictureDescribe(picture.getPictureDescribe());
			//图片的评论
			String commentOfMainPictureTemp =commentOfPictureService.commentsOfPictureTOJson(pictureId);
			if(commentOfMainPictureTemp != null)
			{
				albumDTO.setCommentOfMainPicture(commentOfMainPictureTemp);
			}
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
				//图片的评论
				String commentTemp =commentOfPictureService.commentsOfPictureTOJson(pictures.get(i).getId());
				if(commentTemp != null)
				{
					pictureDTO.setCommentsOfPicture(commentTemp);
				}
				pictureDTO.setPictureId(pictures.get(i).getId());
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
			Collections.reverse(albumDTO.getPictureDTOs());
			albumDTO.setPictureIndex(index-i-1);
			for(int j=index+1;j<pictures.size();j++)
			{
				PictureDTO pictureDTO = new PictureDTO();
				BeanUtils.copyProperties(pictures.get(j), pictureDTO);
				//图片的评论
				String commentTemp =commentOfPictureService.commentsOfPictureTOJson(pictures.get(j).getId());
				if(commentTemp != null)
				{
					pictureDTO.setCommentsOfPicture(commentTemp);
				}
				pictureDTO.setPictureId(pictures.get(j).getId());
				albumDTO.getPictureDTOs().add(pictureDTO);
			}
			
		}
		return albumDTO;
	}
	
	/**
	 * 喜欢图片
	 * 用户喜欢的图片不能是用户自己的图片
	 * 检查该用户是否已喜欢该图片，是则结束
	 * 将喜欢的图片id和用户id放入喜欢图片表中，并将图片表对应图片的likeNumber+1
	 */
	public boolean likePicture(Long pictureId,String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			if(pictureRepository.isPictureOfUser(user.getId(),pictureId)>0)
			{
				return false;
			}
			Picture picture=pictureRepository.findById(pictureId).get();
			if(picture !=null)
			{
				LikePicture likePictureTemp = likePictureRepository.findLikePictureByUserIdAndPictureId(user.getId(), pictureId);
				if (likePictureTemp != null)
				{
					return false;
				}
				LikePicture likePicture = new LikePicture();
				int likeNumber = picture.getLikeNumber() + 1;
				picture.setLikeNumber(likeNumber);
				likePicture.setPictureId(pictureId);
				likePicture.setUserId(user.getId());
				likePictureRepository.save(likePicture);
				return true;
			}
		}
		return false;
	}
		
	/**
	 * 取消喜欢图片
	 * 检查该用户是否已喜欢该图片，否则结束
	 * 根据图片id和用户id删除喜欢图片表内的数据
	 */
	public boolean cancelLikePicture(Long pictureId,String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			LikePicture likePictureTemp = likePictureRepository.findLikePictureByUserIdAndPictureId(user.getId(), pictureId);
			if (likePictureTemp == null)
			{
				return false;
			}
			Picture picture=pictureRepository.findById(pictureId).get();
			if(picture != null)
			{
				Long userId = user.getId();
				int likeNumber = picture.getLikeNumber() - 1;
				pictureRepository.cancelLikePicture(pictureId, likeNumber);
				likePictureRepository.deleteByPictureAndUser(pictureId, userId);
				return true;
			}
		}
		return false;
	}
		
	/**
	 * 收藏图片
	 * 先检查该用户是否拥有该相册
	 * 将收藏图片存入数据库，并将原来的图片的collecteNumber+1
	 */
	public boolean collectPicture(HttpSession session, PictureDTO pictureDTO, String email)
	{
		User user = userRepository.findByEmial(email);
		if(user != null)
		{
			Album album = albumRepository.findAlbumByAlbumIdAndUserId(pictureDTO.getAlbumId(), user.getId());
			if(album != null)
			{
				session.setAttribute("albumId", album.getId());
				Long pictureId = pictureDTO.getPictureId();
				Picture picture=pictureRepository.findById(pictureId).get();
				int collectNumber = picture.getCollectNumber();
				picture.setCollectNumber(collectNumber+1);
				pictureRepository.save(picture);
				savePicture(pictureDTO);
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * 快速收藏图片：收藏图片到最近有收藏图片的相册或最新新建的相册,同时被收藏图片被收藏数+1
	 * 1.从session中查看是否有albumId(需判断session中的albumId是否在相册中存在)
	 * 2.若1.无则查看最新新建的相册
	 * 3.若都无则快速收藏失败
	 * 4.最后要判断相册是否存在，若相册不存在，则创建相册失败
	 * 
	 */
	public boolean quickCollectPicture(HttpSession session,PictureDTO pictureDTO)
	{

		Long albumId = null;
		Long userId = (Long)session.getAttribute("userId");
		if(session.getAttribute("albumId")!=null)
		{
			albumId =(Long) session.getAttribute("albumId");
		}
		else
		{
			List<Album> albums = albumRepository.findAlbumsByUserId(userId);
			Collections.reverse(albums);
			if(albums != null && !albums.isEmpty())
			{
				albumId = albums.get(0).getId();
			}
		}
		
		if(albumId != 0)
		{
			Album album =albumRepository.findById(albumId).get();
			if(album != null)
			{
				Long pictureId = pictureDTO.getPictureId();
				Picture picture=pictureRepository.findById(pictureId).get();
				int collectNumber = picture.getCollectNumber();
				picture.setCollectNumber(collectNumber+1);
				pictureRepository.save(picture);
				pictureDTO.setAlbumId(albumId);
				savePicture(pictureDTO);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 编辑图片
	 */
	public boolean editPicture(Long userId, PictureDTO pictureDTO)
	{
		Long pictureId = pictureDTO.getPictureId();
		if(pictureId != null && pictureId != 0)
		{
			if(pictureRepository.isPictureOfUser(userId, pictureId)>0)
			{
				Picture picture = pictureRepository.findById(pictureId).get();
				picture.setAlbumId(pictureDTO.getAlbumId());
				picture.setPictureDescribe(pictureDTO.getPictureDescribe());
				picture.setPictureLabel(pictureDTO.getPictureLabel());
				pictureRepository.save(picture);
				return true;
			}
		}
		return false;
	}
	/**
	 * 删除照片
	 */
	public boolean deletePicture(Long userId, PictureDTO pictureDTO)
	{
		Long pictureId = pictureDTO.getPictureId();
		if(pictureId != null && pictureId != 0)
		{
			if(pictureRepository.isPictureOfUser(userId, pictureId)>0)
			{
				pictureRepository.deleteById(pictureId);
				return true;
			}
		}
		return false;
	}
}
