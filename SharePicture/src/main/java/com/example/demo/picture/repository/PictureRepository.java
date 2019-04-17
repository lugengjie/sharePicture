 package com.example.demo.picture.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
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
	
	@Query("select count(*) from Picture p,Album a,User u where u.id=a.userId and a.id=p.albumId and u.id=?1")
	public int findPictureNumberByUserId(Long userId);
	
	@Query("select count(*) from Picture p,Album a,User u where u.id=a.userId and a.id=p.albumId and u.id=?1 and p.id=?2")
	public int isPictureOfUser(Long userId, Long pictureId);
	
	//根据兴趣名和限制条数查询PictureDTO
	@Query(value="select distinct p.id,p.pictureDescribe,p.pictureName,p.likeNumber,p.collectNumber,p.pictureLabel,a.id,a.albumTitle,u.id,u.name,u.userPicture from Picture p,Album a,User u  where a.userId=u.id and a.id=p.albumId and a.albumClassification=?1 order by (p.likeNumber+p.collectNumber) desc")
	public List<Object> findPictureDTOByInterestNameAndPageable(String interestName, Pageable pageable);

}
