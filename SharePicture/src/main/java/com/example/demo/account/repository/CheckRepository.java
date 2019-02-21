package com.example.demo.account.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.account.entity.Check;
import com.example.demo.account.entity.User;
@Repository
public interface CheckRepository extends CrudRepository<Check, Long>{
	@Modifying
	@Query("delete from Check c where c.user.id=?1")
	public void deleteCheckByUserId(Long id);
	@Query("from Check c where c.user.id=?1")
	public Check findCheckByUserId(Long id);
	@Query("from Check c where c.activationCode=?1")
	public Check findCheckByActivateCode(String ActivateCode);
}
 