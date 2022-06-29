package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_type")
@JsonIgnoreProperties({"users"})
public class UserType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_type_id")
	private int user_type_id;
	
	@Column(unique=true, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserTypeEnum user_type;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="userType", fetch=FetchType.LAZY)
	private List<User> users = new ArrayList<User>();

	public UserType() {
		super();
	}

	public UserType(int user_type_id, UserTypeEnum user_type, List<User> users) {
		super();
		this.user_type_id = user_type_id;
		this.user_type = user_type;
		this.users = users;
	}

	public UserType(UserTypeEnum user_type, List<User> users) {
		super();
		this.user_type = user_type;
		this.users = users;
	}

	public int getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}

	public UserTypeEnum getUser_type() {
		return user_type;
	}

	public void setUser_type(UserTypeEnum user_type) {
		this.user_type = user_type;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user_type, user_type_id, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserType other = (UserType) obj;
		return user_type == other.user_type && user_type_id == other.user_type_id && Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return "UserType [user_type_id=" + user_type_id + ", user_type=" + user_type + ", users=" + users + "]";
	}
	
	

}
