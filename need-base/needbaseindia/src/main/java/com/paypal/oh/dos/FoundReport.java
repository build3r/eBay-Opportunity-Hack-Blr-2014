package com.paypal.oh.dos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FoundReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    
 	@Column(name="case_id")
 	private String caseId;
 	
    @Column(name="subject_first_name")
    private String subjectFirstName;
     
    @Column(name="subject_last_name")
    private String subjectLastName;
    
    @Column(name = "subject_middle_name")
    private String subjectMiddleName;
    private String subjectNickName;
    
    private String subjectGender;
    private String subjectPhotoURL;
   /* private Date missingDate;
    private Date subjectDOB;
    private Date subjectFoundDate;*/
    private String subjectLanguage;
    private String subjectMissingState;
    private String subjectMissingCity;
    private String subjectFoundState;
    private String subjectFoundCity;
    private String reporterEmail;
    private String reporterPhoneNumber;
    private String guardianFirstName;
    private String guardianMiddleName;
    private String guardianLastName;
    private String guardianRelation;
    private String nativeState;
    private String nativeCity;
    private String nativeDistrict;
    private String nativeAddress;
    private Date creationDate;
    
    
    public FoundReport() {
    	
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getSubjetFirstName() {
		return subjectFirstName;
	}

	public String getSubjectNickName() {
		return subjectNickName;
	}

	public void setSubjectNickName(String subjectNickName) {
		this.subjectNickName = subjectNickName;
	}

	public String getSubjectGender() {
		return subjectGender;
	}

	public void setSubjectGender(String subjectGender) {
		this.subjectGender = subjectGender;
	}

	public String getSubjectPhotoURL() {
		return subjectPhotoURL;
	}

	public void setSubjectPhotoURL(String subjectPhotoURL) {
		this.subjectPhotoURL = subjectPhotoURL;
	}

	/*public Date getMissingDate() {
		return missingDate;
	}

	public void setMissingDate(Date missingDate) {
		this.missingDate = missingDate;
	}

	public Date getSubjectDOB() {
		return subjectDOB;
	}

	public void setSubjectDOB(Date subjectDOB) {
		this.subjectDOB = subjectDOB;
	}*/

	public String getSubjectLanguage() {
		return subjectLanguage;
	}

	public void setSubjectLanguage(String subjectLanguage) {
		this.subjectLanguage = subjectLanguage;
	}

	public String getSubjectMissingState() {
		return subjectMissingState;
	}

	public void setSubjectMissingState(String subjectMissingState) {
		this.subjectMissingState = subjectMissingState;
	}

	public String getSubjectMissingCity() {
		return subjectMissingCity;
	}

	public void setSubjectMissingCity(String subjectMissingCity) {
		this.subjectMissingCity = subjectMissingCity;
	}

	public String getContactEmail() {
		return reporterEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.reporterEmail = contactEmail;
	}

	public String getReporterPhoneNumber() {
		return reporterPhoneNumber;
	}

	public void setReporterPhoneNumber(String reporterPhoneNumber) {
		this.reporterPhoneNumber = reporterPhoneNumber;
	}

	public String getGuardianFirstName() {
		return guardianFirstName;
	}

	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}

	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}

	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}

	public String getGuardianLastName() {
		return guardianLastName;
	}

	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
	}

	public String getGuardianRelation() {
		return guardianRelation;
	}

	public void setGuardianRelation(String guardianRelation) {
		this.guardianRelation = guardianRelation;
	}

	public String getNativeState() {
		return nativeState;
	}

	public void setNativeState(String nativeState) {
		this.nativeState = nativeState;
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	public String getNativeDistrict() {
		return nativeDistrict;
	}

	public void setNativeDistrict(String nativeDistrict) {
		this.nativeDistrict = nativeDistrict;
	}

	public String getNativeAddress() {
		return nativeAddress;
	}

	public String getSubjectFirstName() {
		return subjectFirstName;
	}

	public void setSubjectFirstName(String subjectFirstName) {
		this.subjectFirstName = subjectFirstName;
	}
/*
	public Date getSubjectFoundDate() {
		return subjectFoundDate;
	}

	public void setSubjectFoundDate(Date subjectFoundDate) {
		this.subjectFoundDate = subjectFoundDate;
	}*/

	public String getSubjectFoundState() {
		return subjectFoundState;
	}

	public void setSubjectFoundState(String subjectFoundState) {
		this.subjectFoundState = subjectFoundState;
	}

	public String getSubjectFoundCity() {
		return subjectFoundCity;
	}

	public void setSubjectFoundCity(String subjectFoundCity) {
		this.subjectFoundCity = subjectFoundCity;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setSubjetFirstName(String subjetFirstName) {
		this.subjectFirstName = subjetFirstName;
	}

	public String getSubjectLastName() {
		return subjectLastName;
	}

	public void setSubjectLastName(String subjectLastName) {
		this.subjectLastName = subjectLastName;
	}

	public String getSubjectMiddleName() {
		return subjectMiddleName;
	}

	public void setSubjectMiddleName(String subjectMiddleName) {
		this.subjectMiddleName = subjectMiddleName;
	}
	
	@Override
	public String toString() {
		return this.subjectFirstName + this.subjectLastName;
	}
	
}
