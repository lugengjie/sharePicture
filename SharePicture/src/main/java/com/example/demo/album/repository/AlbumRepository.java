package com.example.demo.album.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.album.entity.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>
{
	
}
