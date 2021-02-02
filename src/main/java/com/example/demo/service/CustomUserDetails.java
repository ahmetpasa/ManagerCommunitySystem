package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dao.FamilyMember;
import com.example.demo.dao.Managers;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	private Managers manager;
	
	public CustomUserDetails(Managers manager) {
		this.manager = manager;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		manager.getRoles().forEach(r -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
			authorities.add(authority);
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return manager.getPassword();
	}

	@Override
	public String getUsername() {
		return manager.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getNameandSurname() {
		String last = manager.getName() + " " + manager.getSurname();
		return last;
	}
	public ArrayList<String> getAllDetails(){
		ArrayList<String> user = new ArrayList<String>();
		user.add(String.valueOf(manager.getManager_ID()));
		user.add(manager.getName());
		user.add(manager.getSurname());
		user.add(manager.getEmail());
		user.add(manager.getAddress());
		user.add(manager.getTelephone());
		user.add(String.valueOf(manager.getBirthday()));
		return user;
	}
	public Managers getWholeManager() {
		return manager;
	}
	
	public Set<FamilyMember> getFamilyMembers(){
		return manager.getMembers();
	}

}
