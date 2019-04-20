package com.example.demo.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.communication.entity.CommentOfPicture;
import com.example.demo.picture.entity.Picture;

@Repository
public interface CommentOfPictureRepository extends CrudRepository<CommentOfPicture, Long>
{
	@Query("from CommentOfPicture c where c.pictureId=?1")
	public List<CommentOfPicture> findCommentsByPictureId(Long pictureId);
}
