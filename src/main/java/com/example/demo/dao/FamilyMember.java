package com.example.demo.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "family")
public class FamilyMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="memberid")
	private long memberID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="managerID", nullable=false)
	@JsonBackReference
	private Managers manager;
	
	private int degree;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	@Column(columnDefinition="VARCHAR(20)",name = "telephoneno")
	private String telephone;
	
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")	
	private Date birthday;

	public FamilyMember() {
		
	}	

	public long getMemberID() {
		return memberID;
	}

	public void setMemberID(long memberID) {
		this.memberID = memberID;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getFullName() {
		return name + " " + surname + " - " + telephone + " - " + email +  " - " + degree;
	}	
	
}
