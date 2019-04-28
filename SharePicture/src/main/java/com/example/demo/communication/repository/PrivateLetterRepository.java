package com.example.demo.communication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.communication.entity.CommentOfPicture;
import com.example.demo.communication.entity.PrivateLetter;

public interface PrivateLetterRepository extends CrudRepository<PrivateLetter, Long>
{
	@Query("from PrivateLetter p where (p.senderId=?1 and p.receiverId=?2) or (p.senderId=?2 and p.receiverId=?1) order by p.id")
	public List<PrivateLetter> findPrivateLetters(Long myUserId, Long otherUserId);
	
	//查找未读信息
	@Query("from PrivateLetter p where p.receiverId=?1 and p.isReaded=0 order by p.id desc")
	public List<PrivateLetter> findPrivateLetterRimind(Long myUserId);
	
	@Modifying
	@Query("update PrivateLetter p set p.isReaded=1 where p.receiverId=?1 and p.senderId=?2")
	public void setIsRead(Long myUserId, Long otherUserId);
	
	//查找未读信息数量
	@Query("select count(*) from PrivateLetter p where  p.receiverId=?1 and p.isReaded=0")
	public int findUnreadMessageNum(Long myUserId);
}
