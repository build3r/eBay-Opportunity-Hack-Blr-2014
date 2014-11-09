package com.paypal.oh.dos;

import java.io.Serializable;

public class MatchRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String lostCaseId;
	private String foundCaseId;
	private String creationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLostCaseId() {
		return lostCaseId;
	}

	public void setLostCaseId(String lostCaseId) {
		this.lostCaseId = lostCaseId;
	}

	public String getFoundCaseId() {
		return foundCaseId;
	}

	public void setFoundCaseId(String foundCaseId) {
		this.foundCaseId = foundCaseId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
