package dlsud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_equipment")
public class TransactionEquipment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int transactionId;
	private double equipmentPayment;
	private int equipmentId;
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
	public double getEquipmentPayment() {
		return equipmentPayment;
	}
	public void setEquipmentPayment(double equipmentPayment) {
		this.equipmentPayment = equipmentPayment;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
