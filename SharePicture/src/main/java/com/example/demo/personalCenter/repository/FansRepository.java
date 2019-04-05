package com.example.demo.personalCenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.picture.entity.Picture;

@Repository
public interface FansRepository extends CrudRepository<Fans, Long>
{
	@Query("from Fans f where f.userId=?1 and f.fansId=?2")
	public Fans findByUserIdAndFansId(Long userId, Long fansId);
}
