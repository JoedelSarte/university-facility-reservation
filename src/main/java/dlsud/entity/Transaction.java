package dlsud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String referenceNumber;
	private int facilityId;
	private int activityId;
	private int userId;
	private Integer reservedCapacity;
	private int personnelHired;
	private String activityStartTime;
	private String activityEndTime;
	private String activityTotalTime;
	private int isApproved;
	private String eventName;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getPersonnelHired() {
		return personnelHired;
	}
	public void setPersonnelHired(int personnelHired) {
		this.personnelHired = personnelHired;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getActivityTotalTime() {
		return activityTotalTime;
	}
	public void setActivityTotalTime(String activityTotalTime) {
		this.activityTotalTime = activityTotalTime;
	}
	public void setReservedCapacity(Integer reservedCapacity) {
		this.reservedCapacity = reservedCapacity;
	}
	public Integer getReservedCapacity() {
		return reservedCapacity;
	}
}
