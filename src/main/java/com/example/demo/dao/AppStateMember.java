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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "appstatemember")
public class AppStateMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appstatememberid")
	private long appStateMemberID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="appointmentID", nullable=false)
	@JsonManagedReference
	private Appointment appointment;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="memberID", nullable=false)
	@JsonBackReference
	private FamilyMember member;
	
	@Column(name="attendancemember")
	private boolean attendance;

	public AppStateMember() {
		
	}

	public long getAppStateMemberID() {
		return appStateMemberID;
	}

	public void setAppStateMemberID(long appStateMemberID) {
		this.appStateMemberID = appStateMemberID;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public FamilyMember getMember() {
		return member;
	}

	public void setMember(FamilyMember member) {
		this.member = member;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

}
