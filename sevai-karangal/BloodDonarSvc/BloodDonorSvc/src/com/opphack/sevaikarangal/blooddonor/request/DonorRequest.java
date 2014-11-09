package com.opphack.sevaikarangal.blooddonor.request;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DonorRequest {
	
	private Long donorId;
	
	private String email;
	
	private String name;
	
	private Long phoneNumber;
	
	private String city;
	
	private String State;

	private String bloodGroup;
	
	private Date lastDonatedDate;
	
	private Date optOutUntilDate;
	
	private double latitude;
	
	private double longitude;
	
	private String locality;
	
	private String gender;
	
	private double height;
	
	private double weight;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getLastDonatedDate() {
		return lastDonatedDate;
	}

	public void setLastDonatedDate(Date lastDonatedDate) {
		this.lastDonatedDate = lastDonatedDate;
	}

	public Date getOptOutUntilDate() {
		return optOutUntilDate;
	}

	public void setOptOutUntilDate(Date optOutUntilDate) {
		this.optOutUntilDate = optOutUntilDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Long getDonorId() {
		return donorId;
	}

	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
