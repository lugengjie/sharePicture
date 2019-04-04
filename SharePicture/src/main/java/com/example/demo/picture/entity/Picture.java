package com.example.demo.picture.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_picture")
public class Picture
{
	private Long id;
	// 标签
	private String pictureLabel;
	// 描述
	private String pictureDescribe;
	// 图片名称
	private String pictureName;
	// 所属相册
	private Long albumId;
	//被喜欢数
	private int likeNumber = 0;
	//被收藏数
	private int collectNumber = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}
	
	public String getPictureLabel()
	{
		return pictureLabel;
	}

	public String getPictureDescribe()
	{
		return pictureDescribe;
	}
	
	
	public String getPictureName()
	{
		return pictureName;
	}	

	public Long getAlbumId()
	{
		return albumId;
	}
	
	
	public int getLikeNumber()
	{
		return likeNumber;
	}

	public int getCollectNumber()
	{
		return collectNumber;
	}

	public void setAlbumId(Long albumId)
	{
		this.albumId = albumId;
	}

	

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setPictureLabel(String pictureLabel)
	{
		this.pictureLabel = pictureLabel;
	}

	public void setPictureDescribe(String pictureDescribe)
	{
		this.pictureDescribe = pictureDescribe;
	}

	public void setPictureName(String pictureName)
	{
		this.pictureName = pictureName;
	}
	
	
	public void setLikeNumber(int likeNumber)
	{
		this.likeNumber = likeNumber;
	}

	public void setCollectNumber(int collectNumber)
	{
		this.collectNumber = collectNumber;
	}

	@Override
	public String toString()
	{
		return "Picture [id=" + id + ", pictureLabel=" + pictureLabel + ", pictureDescribe=" + pictureDescribe
				+ ", pictureName=" + pictureName + ", albumId=" + albumId + ", likeNumber=" + likeNumber
				+ ", collectNumber=" + collectNumber + "]";
	}

}
