package com.example.demo.personalCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.personalCenter.entity.Fans;
import com.example.demo.personalCenter.entity.Interest;
@Repository
public interface InterestRepository extends CrudRepository<Interest, Long>
{
	@Modifying
	@Query("delete from Interest i where i.userId=?1")
	public void deleteInterestByUserId(Long userId);
	
	@Query("select i.interestName from Interest i where i.userId=?1")
	public List<String> findInterestByUserId(Long userId);
}
