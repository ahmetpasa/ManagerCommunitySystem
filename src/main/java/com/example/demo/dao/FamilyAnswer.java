package com.example.demo.dao;

import java.util.List;

public class FamilyAnswer {
	
	private boolean joiningStateFamily;
	
	private String descriptionFamily;
	
	private String[] comingMembers;

	public boolean isJoiningStateFamily() {
		return joiningStateFamily;
	}

	public void setJoiningStateFamily(boolean joiningState) {
		this.joiningStateFamily = joiningState;
	}

	public String getDescriptionFamily() {
		return descriptionFamily;
	}

	public void setDescriptionFamily(String description) {
		this.descriptionFamily = description;
	}

	public String[] getComingMembers() {
		return comingMembers;
	}

	public void setComingMembers(String[] comingMembers) {
		this.comingMembers = comingMembers;
	}

}
