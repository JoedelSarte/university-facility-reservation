package dlsud.request.transaction;

import java.util.List;

import dlsud.entity.Transaction;
import dlsud.utilities.Request;

public class TransactionRequest extends Transaction implements Request {

	private List<Integer> personnelHiredList;
	private String requestDate;

	public List<Integer> getPersonnelHiredList() {
		return personnelHiredList;
	}

	public void setPersonnelHiredList(List<Integer> personnelHiredList) {
		this.personnelHiredList = personnelHiredList;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	
}
