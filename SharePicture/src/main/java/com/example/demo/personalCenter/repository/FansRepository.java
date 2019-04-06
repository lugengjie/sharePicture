package com.example.demo.personalCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.album.entity.Album;
import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.picture.entity.Picture;

@Repository
public interface FansRepository extends CrudRepository<Fans, Long>
{
	@Query("from Fans f where f.userId=?1 and f.fansId=?2")
	public Fans findByUserIdAndFansId(Long userId, Long fansId);
	
	@Query("select a.id from Album a,Fans f where a.userId=f.userId and f.fansId=?1")
	public List<Long> findAlbumIdsOfUsersByFansId(Long fansId);
	
	@Query("select distinct p.id,p.pictureDescribe,p.pictureName,a.id,a.albumTitle,u.id,u.name from Album a,User u,Picture p where a.id in(?1) and a.userId=u.id and p.albumId=a.id order by p.id desc")
	public List<Object> findPictureDTOsOfUserByAlbumIds(List<Long> albumIds);

}
