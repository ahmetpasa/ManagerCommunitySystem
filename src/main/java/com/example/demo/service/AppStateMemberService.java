package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AppStateMember;
import com.example.demo.repository.AppStateMemberRepository;

@Service
@Transactional
public class AppStateMemberService {
	
	@Autowired
	private AppStateMemberRepository asmrepo;
	
	public void save(AppStateMember app) {
        asmrepo.save(app);
    }
	
	public AppStateMember findExists(long memberid, long appointmentid) {
		return asmrepo.findByMemberMemberIDAndAppointmentAppointmentID(memberid, appointmentid);
	}
	
	public long countExists(long memberid, long appointmentid) {
		return asmrepo.countByMemberMemberIDAndAppointmentAppointmentID(memberid, appointmentid);
	}
	
	public List<AppStateMember> findByAppID(long appointmentid){
		return asmrepo.findByAppointmentAppointmentID(appointmentid);
	}
	
	public void delete(AppStateMember app) {
		asmrepo.delete(app);
	}
}
