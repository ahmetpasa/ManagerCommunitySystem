package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AppState;
import com.example.demo.dao.Appointment;
import com.example.demo.repository.AppStateRepository;

@Service
@Transactional
public class AppStateService {
	
	@Autowired
	private AppStateRepository repo;
	
	public void save(AppState app) {
        repo.save(app);
    }
	
	public AppState findExists(long managerid, long appointmentid) {
		return repo.findByManagerManagerIDAndAppointmentAppointmentID(managerid, appointmentid);
	}
	
	public long countExists(long managerid, long appointmentid) {
		return repo.countByManagerManagerIDAndAppointmentAppointmentID(managerid, appointmentid);
	}
	
	public List<AppState> fetchDescription(long appointmentid) {
		return repo.findByJoiningStateFalseAndAppointmentAppointmentID(appointmentid);
	}

}
