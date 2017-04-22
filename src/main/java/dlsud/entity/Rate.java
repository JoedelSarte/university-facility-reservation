package dlsud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate")
public class Rate {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double ratePerHour;
	private int userTypeId;
	private int facilityId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
}
