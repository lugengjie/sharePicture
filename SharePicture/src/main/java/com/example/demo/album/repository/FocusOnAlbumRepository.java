package com.example.demo.album.repository;

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
	
	@Query("select a.albumTitle as albumTitle ,a.id as id from Album a where a.id=?1")
	public Object findTest(Long albumId);
}
