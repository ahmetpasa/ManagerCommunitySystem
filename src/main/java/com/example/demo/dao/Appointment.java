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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "appointment")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class Appointment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="appointmentid")
	private long appointmentID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="managerID", nullable=false)
	@JsonBackReference
	private Managers manager;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateofapp")
	@FutureOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date dateOfApp;
	
	@NotEmpty(message="You must enter the time of appointment.")
	private String time;
	
	@Column(name="descriptionapp")
	@Size(min=3,message="Description must be entered at least 3 characters.")
	private String descriptionApp;
	
	@NotEmpty(message="You must enter the type of appointment.")
	@Column(name="apptype")
	private String appType;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="appointment", fetch=FetchType.EAGER)
	@JsonBackReference
	private Set<AppState> appStates;

	public Appointment(Managers manager, Date dateofApp, String time, String desc, String type) {
		this.manager = manager;
		this.dateOfApp = dateofApp;
		this.time = time;
		this.descriptionApp = desc;
		this.appType = type;
	}

	public Appointment() {
	}

	public long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(long appointment_ID) {
		appointmentID = appointment_ID;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	public Date getDateof_app() {
		return dateOfApp;
	}

	public void setDateof_app(Date dateof_app) {
		this.dateOfApp = dateof_app;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription_app() {
		return descriptionApp;
	}

	public void setDescription_app(String description_app) {
		this.descriptionApp = description_app;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public Set<AppState> getAppStates() {
		return appStates;
	}

	public void setAppStates(Set<AppState> appStates) {
		this.appStates = appStates;
	}
	
}
