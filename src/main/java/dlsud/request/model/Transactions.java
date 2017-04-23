package dlsud.request.model;

public class Transactions {

	private String referenceNumber;
	private String eventName;
	private String userName;
	private String activityStartTime;
	private String activityEndTime;
	private int isApproved;
	private String facilityPayment;
	private String personnelPayment;
	private String totalPayment;
	
	public String getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(String totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getFacilityPayment() {
		return facilityPayment;
	}
	public void setFacilityPayment(String facilityPayment) {
		this.facilityPayment = facilityPayment;
	}
	public String getPersonnelPayment() {
		return personnelPayment;
	}
	public void setPersonnelPayment(String personnelPayment) {
		this.personnelPayment = personnelPayment;
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
}
