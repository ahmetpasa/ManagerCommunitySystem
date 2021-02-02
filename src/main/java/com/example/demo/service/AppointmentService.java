package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.Appointment;
import com.example.demo.dao.FamilyMember;
import com.example.demo.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentService {
	
	@Autowired
	private AppointmentRepository repo;
	
	public List<Appointment> findbyManager(long id){
		return repo.findByManagerManagerID(id);
	}
	
	public List<Appointment> findAll(){
		Sort sort = Sort.by("dateOfApp").ascending().and(Sort.by("time").ascending());
		return repo.findAll(sort);
	}
	
	public void save(Appointment app) {
        repo.save(app);
    }
	
	public Appointment findbyId(long id) {
		return repo.findByAppointmentID(id);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	public Appointment findClosestDateOne() {
		Date date = new Date();
		return repo.findFirstByDateOfAppAfterOrderByDateOfApp(date);
	}
	public long countId(){
		return repo.count();
	}

}
