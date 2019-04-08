package com.example.demo.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.album.entity.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long>
{
	@Query("from Album a where a.userId=?1")
	public List<Album> findAlbumsByUserId(Long id);
	
	@Modifying
	@Query("update Album a set a.albumTitle=?1,a.albumClassification=?2,a.albumDescribe=?3 where a.id=?4")
	public void changeAlbum(String albumTitle, String albumClassification, String albumDescribe, Long id);
	
	@Modifying
	@Query("delete from Album a where a.id=?1")
	public void deleteAlbum(Long albumId);
	
	@Query("from Album a where a.id=?1")
	public Album findAlbumByAlbumId(Long albumId);
	
	@Query("from Album a where a.id=?1 and a.userId=?2")
	public Album findAlbumByAlbumIdAndUserId(Long albumId, Long userId);
	
	@Query("from Album a where a.userId in (?1)")
	public List<Album> findAlbumByUserIds(List<Long> userIds);
	
	@Query("select a.id from Album a where a.userId=?1")
	public List<Long> findAlbumIdsByUserId(Long id);
	
	@Query("select count(*) from Album a where a.userId=?1")
	public int findAlbumNumberByUserId(Long userId);
}
