package com.example.demo.album.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.account.entity.User;
import com.example.demo.picture.entity.Picture;

@Entity
@Table(name = "t_album")
public class Album
{
	private Long id;
	// 标题
	private String albumTitle;
	// 描述
	private String albumDescribe;
	// 类别
	private String albumClassification;
	// 封面
	private String albumCover;
	// 图片数
	private int pictureNumber;
	//用户
	private User user;
	// 图片集合
	private List<Picture> pictures = new ArrayList<Picture>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User getUser()
	{
		return user;
	}

	@Column(nullable=false)
	public String getAlbumTitle()
	{
		return albumTitle;
	}

	public String getAlbumDescribe()
	{
		return albumDescribe;
	}
	
	@Column(nullable=false)
	public String getAlbumClassification()
	{
		return albumClassification;
	}

	public String getAlbumCover()
	{
		return albumCover;
	}

	public int getPictureNumber()
	{
		return pictureNumber;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="album")
	public List<Picture> getPictures()
	{
		return pictures;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}

	public void setAlbumDescribe(String albumDescribe)
	{
		this.albumDescribe = albumDescribe;
	}

	public void setAlbumClassification(String albumClassification)
	{
		this.albumClassification = albumClassification;
	}

	public void setAlbumCover(String albumCover)
	{
		this.albumCover = albumCover;
	}

	public void setPictureNumber(int pictureNumber)
	{
		this.pictureNumber = pictureNumber;
	}

	public void setPictures(List<Picture> pictures)
	{
		this.pictures = pictures;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "Album [id=" + id + ", albumTitle=" + albumTitle + ", albumDescribe=" + albumDescribe
				+ ", albumClassification=" + albumClassification + ", albumCover=" + albumCover + ", pictureNumber="
				+ pictureNumber + ", user=" + user ;
	}
	

}
