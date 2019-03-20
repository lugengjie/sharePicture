package com.example.demo.album.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.picture.entity.Picture;

public class AlbumDTO
{
	private Long id;
	// 标题
	private String albumTitle;
	// 类别
	private String albumClassification;
	// 描述
	private String albumDescribe;
	//封面图片名字
	private String coverPictureName;
	// 图片s
	private List<String> pictureNames=new ArrayList<String>();
	
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

	@Override
	public String toString()
	{
		return "AlbumDTO [id=" + id + ", albumTitle=" + albumTitle + ", albumClassification=" + albumClassification
				+ ", albumDescribe=" + albumDescribe + ", coverPictureName=" + coverPictureName + ", pictureNames="
				+ pictureNames + "]";
	}
}
