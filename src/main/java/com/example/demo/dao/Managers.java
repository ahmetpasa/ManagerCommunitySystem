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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "managers")
public class Managers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "managerid")
	private long managerID;
	
	@Column(name = "email")
	private String email;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "managers_role",
	joinColumns = @JoinColumn(name = "managerid"),
	inverseJoinColumns = @JoinColumn(name = "roleid"))
	@JsonManagedReference
	private Set<Roles> roles;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(columnDefinition="VARCHAR(20)",name = "telephoneno")
	private String telephone;
	
	@Column(name = "address")
	private String address;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate")	
	private Date birthday;
	
	@OneToMany(cascade= CascadeType.REMOVE, mappedBy="manager", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Appointment> apps;
	
	@OneToMany(cascade= CascadeType.REMOVE, mappedBy="manager", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<FamilyMember> members;
	
	@OneToMany(cascade= CascadeType.REMOVE, mappedBy="manager", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<AppState> appStates;
	

	public Managers() {
	}

	public Managers(long manager_ID, String email, String password, Set<Roles> roles,
			String name, String surname, String telephone, String address, Date birthday, Set<Appointment> apps, Set<FamilyMember> members) {
		this.managerID = manager_ID;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.address = address;
		this.birthday = birthday;
		this.apps = apps;
		this.members = members;
	}

	public long getManager_ID() {
		return managerID;
	}

	public void setManager_ID(long manager_ID) {
		this.managerID = manager_ID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
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

	public Set<Appointment> getApps() {
		return apps;
	}

	public void setApps(Set<Appointment> apps) {
		this.apps = apps;
	}

	public Set<FamilyMember> getMembers() {
		return members;
	}

	public void setMembers(Set<FamilyMember> members) {
		this.members = members;
	}

	public Set<AppState> getAppStates() {
		return appStates;
	}

	public void setAppStates(Set<AppState> appStates) {
		this.appStates = appStates;
	}
	
}
