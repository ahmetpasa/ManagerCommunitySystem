package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.AppState;

public interface AppStateRepository extends JpaRepository<AppState, Long> {
	
	AppState findByManagerManagerIDAndAppointmentAppointmentID(long managerid, long appointmentid);
	
	long countByManagerManagerIDAndAppointmentAppointmentID(long managerid, long appointmentid);
	
	List<AppState> findByJoiningStateFalseAndAppointmentAppointmentID(long appointmentid);

}
