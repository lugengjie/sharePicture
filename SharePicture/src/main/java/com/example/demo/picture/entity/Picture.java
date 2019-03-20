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
	
	@Column(nullable=false)
	public String getPictureName()
	{
		return pictureName;
	}	

	public Long getAlbumId()
	{
		return albumId;
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

	@Override
	public String toString()
	{
		return "Picture [id=" + id + ", pictureLabel=" + pictureLabel + ", pictureDescribe=" + pictureDescribe
				+ ", pictureName=" + pictureName + ", albumId=" + albumId + "]";
	}
}
