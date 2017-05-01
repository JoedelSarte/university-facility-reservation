package dlsud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_personnel")
public class TransactionPersonnel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int transactionId;
	private double personnelPayment;
	private int personnelId;
	private double totalAmount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getPersonnelPayment() {
		return personnelPayment;
	}
	public void setPersonnelPayment(double personnelPayment) {
		this.personnelPayment = personnelPayment;
	}
	public int getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(int personnelId) {
		this.personnelId = personnelId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
