package com.rangde.api.model;

public class PatientUpdate {
	
	private String userName;
	private String userProfile;
	private String comment;
	private String mediaUrl;
	private String time;
	private String location;
	
	public PatientUpdate() {
		
	}
	
	public PatientUpdate(String userName, String userProfile, String comment, String mediaUrl, String time, String location) {
		this.userName = userName;
		this.userProfile = userProfile;
		this.comment = comment;
		this.mediaUrl = mediaUrl;
		this.time = time;
		this.location = location;
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
