package com.example.demo.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 激活码表
 * @author Administrator
 *
 */
@Entity
@Table(name="t_check")
public class Check {
	private Long id;
	private Long deadline;
	private User user;
	//激活码
	private String activationCode;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public Long getDeadline() {
		return deadline;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	@Column(nullable=false,unique=true)
	public String getActivationCode() {
		return activationCode;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "Check [id=" + id + ", deadline=" + deadline + ", user=" + user + ", activationCode=" + activationCode
				+ "]";
	}
}
