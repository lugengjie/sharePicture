package com.example.demo.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.account.entity.State;
import com.example.demo.account.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
	@Query("from User u where u.email=?1")
	public User findByEmial(String email);

	@Modifying
	@Query("update User u set u.state=0 where u.id=?1")
	public void changeState(Long id);

	@Modifying
	@Query("update User u set u.password=?1 where u.email=?2")
	public void changePassword(String password, String email);
	
	@Query("from User u where u.id=?1")
	public User findUserByUserId(Long userId);
	
	//模糊查询用户
	@Query("from User u where (u.name like %?1%) or (u.email like %?1%) order by u.id desc")
	public List<User> findUsersByLikeStr(String likeStr);

}
