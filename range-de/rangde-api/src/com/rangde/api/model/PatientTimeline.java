package com.rangde.api.model;

import java.util.List;

public class PatientTimeline {
	
	private String status;
	private List<PatientUpdate> updates;
	
	public PatientTimeline(){
		
	}
	
	public PatientTimeline(String status, List<PatientUpdate> updates) {
		this.status = status;
		this.updates = updates;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<PatientUpdate> getUpdates() {
		return updates;
	}
	public void setUpdates(List<PatientUpdate> updates) {
		this.updates = updates;
	}

}
