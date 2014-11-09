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
import com.opphack.sevaikarangal.blooddonor.request.RequestInfo;

@Entity
public class BloodRequest {

	@Id
	private Long requestId;
	private String bloodGroup;
	private String patientName;
	private String hospital;
	private String contactPerson;
	private Long contactNumber;
	private String locality;
	private String city;
	private Date requestDate;
	private int status;
	private int bloodUnits;
	
	public BloodRequest(){
		super();
	}
	
	private static Objectify getService(){
		 return ObjectifyService.begin();
	}
	
	public Long addRequest(RequestInfo bloodRequestorRequest){
		
		//Set Donor Data from request
		this.requestId = bloodRequestorRequest.getRequestId();
		this.bloodGroup = bloodRequestorRequest.getBloodGroup() != null ? bloodRequestorRequest.getBloodGroup().toLowerCase() : null;
		this.patientName = bloodRequestorRequest.getPatientName();
		this.hospital = bloodRequestorRequest.getHospital();
		this.city = bloodRequestorRequest.getCity() != null ? bloodRequestorRequest.getCity().toLowerCase() : null;
		this.contactPerson = bloodRequestorRequest.getContactPerson();
		this.contactNumber = bloodRequestorRequest.getContactNumber();
		this.requestDate = bloodRequestorRequest.getRequestDate();
		this.status = bloodRequestorRequest.getStatus();
		this.locality = bloodRequestorRequest.getLocality() != null ? bloodRequestorRequest.getLocality().toLowerCase() : null;
		this.bloodUnits = bloodRequestorRequest.getBloodUnits();
		
		Key<BloodRequest> result = getService().put(this);
		return result.getId();
		
	}
	
	public Long updateRequestStatus(Long requestId, int requestStatus){
		BloodRequest result = getService().get(BloodRequest.class,requestId);
		result.status = requestStatus;
		Key<BloodRequest> updateResult = getService().put(result);
		return updateResult.getId();
	}
	
	public RequestInfo getRequestById(Long requestId){
		BloodRequest result = getService().get(BloodRequest.class,requestId);
		return dataMapper(result); 
	}
	
	public List<RequestInfo> getRequestByCityBloodGroup(String city, String bloodGroup, String locality){
		Query<BloodRequest> result = getService().query(BloodRequest.class).filter("bloodGroup", bloodGroup.toLowerCase()).filter("locality", locality.toLowerCase());
		List <BloodRequest> matchList = result.list();
		List<RequestInfo> bloodReqList = new ArrayList<RequestInfo>();
		for(BloodRequest match : matchList){
			bloodReqList.add(dataMapper(match));
		}
		return bloodReqList; 
	}
	
	private RequestInfo dataMapper(BloodRequest bloodRequest){
		RequestInfo resp = new RequestInfo();
		resp.setRequestId(bloodRequest.requestId);
		resp.setCity(bloodRequest.city);
		resp.setContactNumber(bloodRequest.contactNumber);
		resp.setBloodGroup(bloodRequest.bloodGroup);
		resp.setLocality(bloodRequest.locality);
		resp.setBloodGroup(bloodRequest.bloodGroup);
		resp.setBloodUnits(bloodRequest.bloodUnits);
		resp.setContactPerson(bloodRequest.contactPerson);
		resp.setHospital(bloodRequest.hospital);
		resp.setPatientName(bloodRequest.patientName);
		resp.setRequestDate(bloodRequest.requestDate);
		resp.setRequestId(bloodRequest.requestId);
		resp.setStatus(bloodRequest.status);
		return resp;
	}

	public List<RequestInfo> getRequestByPhoneNum(Long phoneNumber) {
		Query<BloodRequest> result = getService().query(BloodRequest.class).filter("contactNumber", phoneNumber);
		List <BloodRequest> matchList = result.list();
		List<RequestInfo> bloodReqList = new ArrayList<RequestInfo>();
		for(BloodRequest match : matchList){
			bloodReqList.add(dataMapper(match));
		}
		return bloodReqList; 
	}
	
}
