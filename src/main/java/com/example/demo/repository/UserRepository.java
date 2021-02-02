package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.FamilyMember;
import com.example.demo.dao.Managers;

@Repository
public interface UserRepository extends CrudRepository<Managers, Long>{
	Managers findByEmail(String username);
	
	Managers findByManagerID(long managerId);	
	
	@Query("select m.managerID from Managers m left join m.appStates a on m.managerID = a.manager.managerID where a.appointment.appointmentID = ?1")
	List<Long> findManagerIDsByAppStateAppointmentID(long id);
	
	List<Managers> findByManagerIDNotInAndRolesRoleId(List<Long> ids, long role_id);
	
	List<Managers> findByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateTrue(long id);
	
	List<Managers> findByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateFalse(long id);
	
	long countByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateTrue(long id);
	
	long countByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateFalse(long id);
	
	long countByManagerIDNotInAndRolesRoleId(List<Long> ids, long role_id);
	
	@Query("select a.name, a.surname, a.email, a.address, a.telephone, b.description from Managers a left join a.appStates b on a.managerID = b.manager.managerID where b.appointment.appointmentID = ?1 and b.joiningState = false")
	List<Object[]> findRejectedUserAndDescriptions(long id);
	
	List<Managers> findByAppStatesAppointmentAppointmentIDAndAppStatesAttendanceTrue(long id);
	
	long countByAppStatesAppointmentAppointmentIDAndAppStatesAttendanceTrue(long id);
}
