package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.dao.AppStateMember;

public interface AppStateMemberRepository extends CrudRepository<AppStateMember, Long>{
	
	AppStateMember findByMemberMemberIDAndAppointmentAppointmentID(long memberid, long appointmentid);
	
	long countByMemberMemberIDAndAppointmentAppointmentID(long memberid, long appointmentid);
	
	List<AppStateMember> findByAppointmentAppointmentID(long appointmentid);

}
