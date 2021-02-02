package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FamilyMember;
import com.example.demo.dao.Managers;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {	
	private UserRepository userRepository;


	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Managers manager = this.userRepository.findByEmail(username);
		CustomUserDetails customUserDetails = null;
		if (manager != null) {
			customUserDetails = new CustomUserDetails(manager);
		} else {
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		return customUserDetails;
	}
	
	public Managers findbyManager(long id){
		return userRepository.findByManagerID(id);
	}
	
	public List<Long> findAnsweredUserIDs(long app_id){
		return userRepository.findManagerIDsByAppStateAppointmentID(app_id);
	}
	
	public List<Managers> findNotAnsweredUsers(long app_id){
		List<Long> ids = findAnsweredUserIDs(app_id);
		long role_id = 2;
		return userRepository.findByManagerIDNotInAndRolesRoleId(ids, role_id);
	}
	public List<Managers> findRejectedUsers(long app_id){
		return userRepository.findByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateFalse(app_id);
	}
	public List<Managers> findAcceptedUsers(long app_id){
		return userRepository.findByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateTrue(app_id);
	}
	public long countAcceptedUsers(long app_id){
		return userRepository.countByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateTrue(app_id);
	}
	public long countRejectedUsers(long app_id){
		return userRepository.countByAppStatesAppointmentAppointmentIDAndAppStatesJoiningStateFalse(app_id);
	}
	public long countNotAnsweredUsers(long app_id){
		List<Long> ids = findAnsweredUserIDs(app_id);
		long role_id = 2;
		return userRepository.countByManagerIDNotInAndRolesRoleId(ids, role_id);
	}
	public List<Object[]> findRejectedUsersAndDescriptions(long id){
		return userRepository.findRejectedUserAndDescriptions(id);
	}
	public List<Managers> findUsersThatCame(long app_id){
		return userRepository.findByAppStatesAppointmentAppointmentIDAndAppStatesAttendanceTrue(app_id);
	}
	public long countUsersThatCame(long app_id){
		return userRepository.countByAppStatesAppointmentAppointmentIDAndAppStatesAttendanceTrue(app_id);
	}
}
