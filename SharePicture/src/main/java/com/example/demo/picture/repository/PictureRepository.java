package com.example.demo.picture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.album.entity.Album;
import com.example.demo.picture.entity.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>
{
	@Query("from Picture p where p.albumId=?1")
	public List<Picture> findPictureByAlbumId(Long albumId);
}
