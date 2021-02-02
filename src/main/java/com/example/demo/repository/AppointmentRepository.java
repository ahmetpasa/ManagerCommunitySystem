package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.dao.Appointment;
import com.example.demo.dao.FamilyMember;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	List<Appointment> findByManagerManagerID(long manager_id);

	Appointment findByAppointmentID(long id);
	
	Appointment findFirstByDateOfAppAfterOrderByDateOfApp(Date now);
	
	List<Appointment> findAll(Sort Sort);
}
