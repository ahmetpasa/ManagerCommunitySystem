package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dao.FamilyMember;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long>{
	
	List<FamilyMember> findByManagerManagerID(long manager_id);
	
	FamilyMember findByMemberID(long memberId);
	
	long countByManagerManagerID(long manager_id);
	
	@Query("select a.memberID,  a.name, a.surname, a.email, a.address, a.telephone, a.birthday, a.degree, b.name, b.surname from FamilyMember a left join a.manager b on a.manager.managerID = b.managerID")
	List<Object[]> findMembersWithManagers();

}
