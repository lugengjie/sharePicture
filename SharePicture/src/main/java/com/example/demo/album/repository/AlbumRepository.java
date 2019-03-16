package com.example.demo.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.album.entity.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>
{
	@Query("from Album a where a.userId=?1")
	public List<Album> findAlbumsByUserId(Long id);
}
