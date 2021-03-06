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
	@Query("from Album a where a.userId=?1 order by a.id desc")
	public List<Album> findAlbumsByUserId(Long id);
	
	//通过标签，描述，类别模糊查询
	@Query("from Album a where (a.albumTitle like %?1%) or (a.albumDescribe like %?1%) or (a.albumClassification like %?1%) order by a.id desc")
	public List<Album> reseachAlbumsByLike(String likeStr);
	
	
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
	
	@Query("select a.id,a.albumTitle,a.albumDescribe,u.id,u.name,u.userPicture from Album a,User u where a.id in (?1) and a.userId = u.id order by a.id desc")
	public List<Object> findAlbumDTOByAlbumIds(List<Long> albumIds);
	
	
	
}
