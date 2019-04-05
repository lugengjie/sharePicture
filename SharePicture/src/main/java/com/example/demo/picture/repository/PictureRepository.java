 package com.example.demo.picture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.picture.entity.LikePicture;
import com.example.demo.picture.entity.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long>
{
	@Query("from Picture p where p.albumId=?1")
	public List<Picture> findPictureByAlbumId(Long albumId);
	
	@Query("from Picture p where p.pictureName=?1")
	public Picture findPictureByPictureName(String pictureName);
	
	@Modifying
	@Query("update Picture p set p.likeNumber=?2 where p.id=?1")
	public void cancelLikePicture(Long pictureId, int likeNumber);

}
