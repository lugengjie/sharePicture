package com.example.demo.picture.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.album.entity.Album;
import com.example.demo.picture.entity.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>
{

}
