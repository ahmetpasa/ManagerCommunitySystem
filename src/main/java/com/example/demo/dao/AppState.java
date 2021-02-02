package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "appstate")
public class AppState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appstateid")
	private long appStateID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="appointmentID", nullable=false)
	@JsonManagedReference
	private Appointment appointment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="managerID", nullable=false)
	@JsonBackReference
	private Managers manager;
	
	@Column(name="joiningstate")
	private boolean joiningState;
	
	@Column(nullable=true)
	private String description;
	
	@Column(name="attendance")
	private boolean attendance;

	public AppState() {
	}

	public long getAppStateID() {
		return appStateID;
	}

	public void setAppStateID(long appStateID) {
		this.appStateID = appStateID;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	public boolean isJoiningState() {
		return joiningState;
	}

	public void setJoiningState(boolean joiningState) {
		this.joiningState = joiningState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}	
		
}
