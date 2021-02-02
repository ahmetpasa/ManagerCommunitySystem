package com.example.demo.dao;

public class ManagerWithSuccess {
	
	private Managers manager;
	
	private boolean success;

	public ManagerWithSuccess(Managers manager, boolean success) {
		this.manager = manager;
		this.success = success;
	}

	public Managers getManager() {
		return manager;
	}

	public void setManager(Managers manager) {
		this.manager = manager;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
