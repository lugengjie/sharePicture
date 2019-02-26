package com.example.demo.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.account.entity.State;

@Entity
@Table(name = "t_user")
public class User
{
	private Long id;
	private String email;
	private String name;
	private String password;
	// 是否账号是否已经激活
	private State state = State.NO;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}

	@Column(nullable = false, unique = true)
	public String getEmail()
	{
		return email;
	}

	public String getName()
	{
		return name;
	}

	@Column(nullable = false)
	public String getPassword()
	{
		return password;
	}

	public State getState()
	{
		return state;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setEmail(String emial)
	{
		this.email = emial;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", emial=" + email + ", name=" + name + ", password=" + password + ", state=" + state
				+ "]";
	}

}
