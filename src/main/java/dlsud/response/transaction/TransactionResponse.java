package dlsud.response.transaction;

import java.util.List;

import dlsud.request.model.Transactions;
import dlsud.utilities.AbstractResponse;

public class TransactionResponse  extends AbstractResponse {

	private List<Transactions> transactions;
	private String referenceNumber;
	private String eventName;
	private Double eventPrice;
	private Double personnelPrice;
	private String userName;
	private List<String> personnelList;
	private String eventFacility;
	private Double totalPrice;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<String> getPersonnelList() {
		return personnelList;
	}

	public void setPersonnelList(List<String> personnelList) {
		this.personnelList = personnelList;
	}

	public String getEventFacility() {
		return eventFacility;
	}

	public void setEventFacility(String eventFacility) {
		this.eventFacility = eventFacility;
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

	public Double getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Double eventPrice) {
		this.eventPrice = eventPrice;
	}

	public Double getPersonnelPrice() {
		return personnelPrice;
	}

	public void setPersonnelPrice(Double personnelPrice) {
		this.personnelPrice = personnelPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
	
}
