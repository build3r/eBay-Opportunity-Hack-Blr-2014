package com.opphack.sevaikarangal.blooddonor.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.annotation.Entity;
import com.opphack.sevaikarangal.blooddonor.request.DonorRequest;

@Entity
public class Donor {

	@Id
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
	
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	public Donor(){
		super();
	}
	
	private static Objectify getService(){
		 return ObjectifyService.begin();
	}
	
	public Long addDonor(DonorRequest donorRequest){
		
		//Set Donor Data from request
		this.donorId = donorRequest.getDonorId();
		this.email = donorRequest.getEmail();
		this.name = donorRequest.getName();
		this.phoneNumber = donorRequest.getPhoneNumber();
		this.city = donorRequest.getCity() != null ? donorRequest.getCity().toLowerCase() : null;
		this.State = donorRequest.getState();
		this.bloodGroup = donorRequest.getBloodGroup() != null ? donorRequest.getBloodGroup().toLowerCase() : null;
		this.lastDonatedDate = donorRequest.getLastDonatedDate();
		this.optOutUntilDate = donorRequest.getOptOutUntilDate();
		this.latitude = donorRequest.getLatitude();
		this.longitude = donorRequest.getLongitude();
		this.locality = donorRequest.getLocality() != null ? donorRequest.getLocality().toLowerCase() : null;
		this.gender = donorRequest.getGender();
		this.height = donorRequest.getHeight();
		this.weight = donorRequest.getWeight();
		this.createdDate = new Date();
		this.lastModifiedDate = new Date();
		
		Key<Donor> result = getService().put(this);
		return result.getId();
		
	}
	
	public DonorRequest getDonorById(Long donorId){
		Donor result = getService().get(Donor.class,donorId);
		return dataMapper(result); 
	}
	
	public List<DonorRequest> getDonorByCityBloodGroup(String city, String bloodGroup,String locality){
		Query<Donor> result = getService().query(Donor.class);//.filter("bloodGroup", bloodGroup.toLowerCase()).filter("locality", locality.toLowerCase());
		List <Donor> matchList = result.list();
		List<DonorRequest> donorReqList = new ArrayList<DonorRequest>();
		for(Donor match : matchList){
			donorReqList.add(dataMapper(match));
		}
		return donorReqList; 
	}
	
	private DonorRequest dataMapper(Donor donor){
		DonorRequest resp = new DonorRequest();
		resp.setDonorId(donor.donorId);
		resp.setEmail(donor.email);
		resp.setName(donor.name);
		resp.setCity(donor.city);
		resp.setPhoneNumber(donor.phoneNumber);
		resp.setState(donor.State);
		resp.setBloodGroup(donor.bloodGroup);
		resp.setLastDonatedDate(donor.lastDonatedDate);
		resp.setOptOutUntilDate(donor.optOutUntilDate);
		resp.setGender(donor.gender);
		resp.setHeight(donor.height);
		resp.setWeight(donor.weight);
		resp.setLatitude(donor.latitude);
		resp.setLongitude(donor.longitude);
		resp.setLocality(donor.locality);
		return resp;
	}

	public Long updateDonorLatLong(Long donorId2, double latitude2,
			double longitude2) {
		Donor result = getService().get(Donor.class,donorId2);
		result.latitude = latitude2;
		result.longitude = longitude2;
		Key<Donor> updateDonorResult = getService().put(result);
		return updateDonorResult.getId();
	}
}
