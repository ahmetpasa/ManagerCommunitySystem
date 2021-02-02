package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.FamilyMember;
import com.example.demo.repository.FamilyMemberRepository;

@Service
@Transactional
public class FamilyMemberService {
	
	@Autowired
	private FamilyMemberRepository fmrepo;
	
	public void save(FamilyMember fm) {
		fmrepo.save(fm);
	}
	
	public List<FamilyMember> findbyManager(long id){
		return fmrepo.findByManagerManagerID(id);
	}
	
	public FamilyMember findbyId(long id) {
		return fmrepo.findByMemberID(id);
	}
	
	public void delete(long id) {
		fmrepo.deleteById(id);
	}
	public long countMembers(long id) {
		return fmrepo.countByManagerManagerID(id);
	}
	
	public List<Object[]> findMembersWithManagers(){
		return fmrepo.findMembersWithManagers();
	}
	
	public long countAllMembers() {
		return fmrepo.count();
	}

}
