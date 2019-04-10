package com.example.demo.picture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.picture.entity.LikePicture;
import com.example.demo.picture.entity.Picture;

@Repository
public interface LikePictureRepository extends CrudRepository<LikePicture, Long>
{
	@Modifying
	@Query("delete from LikePicture l where l.pictureId=?1 and l.userId=?2")
	public void deleteByPictureAndUser(Long pictureId, Long userId);
	
	@Query("from LikePicture l where l.userId=?1 and l.pictureId=?2")
	public LikePicture findLikePictureByUserIdAndPictureId(Long userId, Long pictureId);
	
	@Query("select l.pictureId from LikePicture l where l.userId=?1")
	public List<Long> findLikePictureIdsByUserId(Long userId);
}
