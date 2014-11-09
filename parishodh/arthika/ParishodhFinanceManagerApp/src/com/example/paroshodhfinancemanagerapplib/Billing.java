package com.example.paroshodhfinancemanagerapplib;

public class Billing {

	private String chapter;
	private String project;
	private String purchasingDate;
	private String purpose;
	private String storeName;
	private String amount;
	private String billNumber;
	
	
	public String getChapter() {
		return chapter;
	}
	public void setChapter(CharSequence charSequence) {
		this.chapter = (String) charSequence;
	}
	public String getProject() {
		return project;
	}
	public void setProject(CharSequence charSequence) {
		this.project = (String) charSequence;
	}
	public String getPurchasingDate() {
		return purchasingDate;
	}
	public void setPurchasingDate(CharSequence charSequence) {
		this.purchasingDate = (String) charSequence;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(CharSequence charSequence) {
		this.purpose = (String) charSequence;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(CharSequence charSequence) {
		this.storeName = (String) charSequence;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(CharSequence charSequence) {
		this.amount = (String) charSequence;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(CharSequence charSequence) {
		this.billNumber = (String) charSequence;
	}
	
}
