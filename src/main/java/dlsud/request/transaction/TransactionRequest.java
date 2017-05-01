package dlsud.request.transaction;

import java.util.List;

import dlsud.entity.Transaction;
import dlsud.entity.TransactionEquipment;
import dlsud.entity.TransactionPersonnel;
import dlsud.utilities.Request;

public class TransactionRequest extends Transaction implements Request {

	private double equipmentTotalPayment;
	private double personnelTotalPayment;
	private List<TransactionEquipment> equipmentList;
	private List<TransactionPersonnel> personnelList;
	private String requestDate;
	private int status;
	
	public double getEquipmentTotalPayment() {
		return equipmentTotalPayment;
	}
	public void setEquipmentTotalPayment(double equipmentTotalPayment) {
		this.equipmentTotalPayment = equipmentTotalPayment;
	}
	public double getPersonnelTotalPayment() {
		return personnelTotalPayment;
	}
	public void setPersonnelTotalPayment(double personnelTotalPayment) {
		this.personnelTotalPayment = personnelTotalPayment;
	}
	public List<TransactionEquipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<TransactionEquipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public List<TransactionPersonnel> getPersonnelList() {
		return personnelList;
	}
	public void setPersonnelList(List<TransactionPersonnel> personnelList) {
		this.personnelList = personnelList;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
