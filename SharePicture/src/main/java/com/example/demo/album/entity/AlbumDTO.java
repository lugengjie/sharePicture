package com.example.demo.album.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.picture.entity.Picture;
import com.example.demo.picture.entity.PictureDTO;

public class AlbumDTO
{
	private Long id;
	// 标题
	private String albumTitle="";
	// 类别
	private String albumClassification="";
	// 描述
	private String albumDescribe="";
	//封面图片名字
	private String coverPictureName="";
	// 图片s
	private List<String> pictureNames=new ArrayList<String>();
	//相册所属用户名
	private String userName="";
	//相册所属用户头像
	private String userPicture="";
	//相册所属用户id
	private Long userId;
	//轮播图片索引
	private int pictureIndex;
	//轮播图片描述
	private String mainPictureDescribe;
	//是否是自己的相册 1_是   0_不是
	private int isMyAlbum;
	//被关注数
	private int focusNumber;
	//图片DTO
	private List<PictureDTO> pictureDTOs = new ArrayList<PictureDTO>();
	// 图片数
	private int pictureNumber;
	//是否已关注 1_是   0_不是
	private int isFocusOn;
	private String touristPicture;
	private String touristName;;
	private Long touristId;
	private Long realPictureId;
	private String commentOfMainPicture;
	
	
	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAlbumTitle()
	{
		return albumTitle;
	}
	
	public String getAlbumClassification()
	{
		return albumClassification;
	}
	
	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}
	
	public void setAlbumClassification(String albumClassification)
	{
		this.albumClassification = albumClassification;
	}

	public List<String> getPictureNames()
	{
		return pictureNames;
	}

	public void setPictureNames(List<String> pictureNames)
	{
		this.pictureNames = pictureNames;
	}

	public String getCoverPictureName()
	{
		return coverPictureName;
	}

	public void setCoverPictureName(String coverPictureName)
	{
		this.coverPictureName = coverPictureName;
	}
	
	public String getAlbumDescribe()
	{
		return albumDescribe;
	}

	public void setAlbumDescribe(String albumDescribe)
	{
		this.albumDescribe = albumDescribe;
	}

	
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	
	public List<PictureDTO> getPictureDTOs()
	{
		return pictureDTOs;
	}

	public void setPictureDTOs(List<PictureDTO> pictureDTOs)
	{
		this.pictureDTOs = pictureDTOs;
	}

	
	
	public int getPictureIndex()
	{
		return pictureIndex;
	}

	public void setPictureIndex(int pictureIndex)
	{
		this.pictureIndex = pictureIndex;
	}

	
	public String getUserPicture()
	{
		return userPicture;
	}

	public void setUserPicture(String userPicture)
	{
		this.userPicture = userPicture;
	}
	
	
	public String getMainPictureDescribe()
	{
		return mainPictureDescribe;
	}

	public void setMainPictureDescribe(String mainPictureDescribe)
	{
		this.mainPictureDescribe = mainPictureDescribe;
	}

	
	public int getIsMyAlbum()
	{
		return isMyAlbum;
	}

	public void setIsMyAlbum(int isMyAlbum)
	{
		this.isMyAlbum = isMyAlbum;
	}

	
	public int getFocusNumber()
	{
		return focusNumber;
	}

	public void setFocusNumber(int focusNumber)
	{
		this.focusNumber = focusNumber;
	}

	
	public int getPictureNumber()
	{
		return pictureNumber;
	}

	public void setPictureNumber(int pictureNumber)
	{
		this.pictureNumber = pictureNumber;
	}

	
	public int getIsFocusOn()
	{
		return isFocusOn;
	}

	public void setIsFocusOn(int isFocusOn)
	{
		this.isFocusOn = isFocusOn;
	}

	
	public String getTouristPicture()
	{
		return touristPicture;
	}

	public void setTouristPicture(String touristPicture)
	{
		this.touristPicture = touristPicture;
	}

	public String getTouristName()
	{
		return touristName;
	}

	public void setTouristName(String touristName)
	{
		this.touristName = touristName;
	}

	public Long getTouristId()
	{
		return touristId;
	}

	public void setTouristId(Long touristId)
	{
		this.touristId = touristId;
	}
	
	
	public Long getRealPictureId()
	{
		return realPictureId;
	}

	public void setRealPictureId(Long realPictureId)
	{
		this.realPictureId = realPictureId;
	}

	
	public String getCommentOfMainPicture()
	{
		return commentOfMainPicture;
	}

	public void setCommentOfMainPicture(String commentOfMainPicture)
	{
		this.commentOfMainPicture = commentOfMainPicture;
	}

	@Override
	public String toString()
	{
		return "AlbumDTO [id=" + id + ", albumTitle=" + albumTitle + ", albumClassification=" + albumClassification
				+ ", albumDescribe=" + albumDescribe + ", coverPictureName=" + coverPictureName + ", pictureNames="
				+ pictureNames + ", userName=" + userName + ", userPicture=" + userPicture + ", userId=" + userId
				+ ", pictureIndex=" + pictureIndex + ", mainPictureDescribe=" + mainPictureDescribe + ", isMyAlbum="
				+ isMyAlbum + ", focusNumber=" + focusNumber + ", pictureDTOs=" + pictureDTOs + ", pictureNumber="
				+ pictureNumber + ", isFocusOn=" + isFocusOn + ", touristPicture=" + touristPicture + ", touristName="
				+ touristName + ", touristId=" + touristId + ", realPictureId=" + realPictureId
				+ ", commentOfMainPicture=" + commentOfMainPicture + "]";
	}
}
