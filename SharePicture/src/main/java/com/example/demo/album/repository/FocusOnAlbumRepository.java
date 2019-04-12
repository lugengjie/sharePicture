package com.example.demo.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.album.entity.Album;
import com.example.demo.album.entity.AlbumTesta;
import com.example.demo.album.entity.FocusOnAlbum;

@Repository
public interface FocusOnAlbumRepository extends CrudRepository<FocusOnAlbum, Long>
{
	@Query("from FocusOnAlbum f where f.albumId=?1 and f.userId=?2")
	public FocusOnAlbum findFocusOnAlbumByAlbumIdAndUserId(Long albumId, Long userId);
	
	@Query("select f.albumId from FocusOnAlbum f where f.userId=?1")
	public List<Long> findFocusOnAlbumIdsByUserId(Long userId);
	
	@Query("select f.userId from FocusOnAlbum f where f.albumId=?1")
	public List<Long> findFocusOnUserIdsByAlbumId(Long albumId);
	
}
