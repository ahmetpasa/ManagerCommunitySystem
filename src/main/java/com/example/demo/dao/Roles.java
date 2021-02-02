package com.example.demo.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="role")
public class Roles {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="roleid")
	private Long roleId;
	
	private String name;
	
	@ManyToMany(mappedBy = "roles", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Managers> users;

	public Long getRole_id() {
		return roleId;
	}

	public void setRole_id(Long role_id) {
		this.roleId = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Managers> getUsers() {
		return users;
	}

	public void setUsers(Set<Managers> users) {
		this.users = users;
	}
	
	

}
