package dlsud.request.model;

import java.util.List;

import dlsud.entity.TransactionEquipment;
import dlsud.entity.TransactionPersonnel;

public class Transactions {

	private String referenceNumber;
	private String eventName;
	private String userName;
	private String activityStartTime;
	private String activityEndTime;
	private int isApproved;
	private double facilityPayment;
	private double totalPayment;
	private double equipmentPayment;
	private double personnelPayment;
	private int isPaid;
	private List<TransactionEquipment> transactionEquipmentList;
	private List<TransactionPersonnel> transactionPersonnelList;
	
	public double getFacilityPayment() {
		return facilityPayment;
	}
	public void setFacilityPayment(double facilityPayment) {
		this.facilityPayment = facilityPayment;
	}
	public double getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	public List<TransactionEquipment> getTransactionEquipmentList() {
		return transactionEquipmentList;
	}
	public void setTransactionEquipmentList(List<TransactionEquipment> transactionEquipmentList) {
		this.transactionEquipmentList = transactionEquipmentList;
	}
	public List<TransactionPersonnel> getTransactionPersonnelList() {
		return transactionPersonnelList;
	}
	public void setTransactionPersonnelList(List<TransactionPersonnel> transactionPersonnelList) {
		this.transactionPersonnelList = transactionPersonnelList;
	}
	public double getEquipmentPayment() {
		return equipmentPayment;
	}
	public void setEquipmentPayment(double equipmentPayment) {
		this.equipmentPayment = equipmentPayment;
	}
	public double getPersonnelPayment() {
		return personnelPayment;
	}
	public void setPersonnelPayment(double personnelPayment) {
		this.personnelPayment = personnelPayment;
	}
	
}
