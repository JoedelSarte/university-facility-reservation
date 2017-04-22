package dlsud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_details")
public class TransactionDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int transactionId;
	private double transactionPayment;
	private int transactionRateId;
	private int personnelRateId;
	private int isPaid;
	
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
	public double getTransactionPayment() {
		return transactionPayment;
	}
	public void setTransactionPayment(double transactionPayment) {
		this.transactionPayment = transactionPayment;
	}
	public int getTransactionRateId() {
		return transactionRateId;
	}
	public void setTransactionRateId(int transactionRateId) {
		this.transactionRateId = transactionRateId;
	}
	public int getPersonnelRateId() {
		return personnelRateId;
	}
	public void setPersonnelRateId(int personnelRateId) {
		this.personnelRateId = personnelRateId;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
}
