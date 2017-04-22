package dlsud.response.transaction;

import java.util.List;

import dlsud.request.model.Transactions;
import dlsud.utilities.AbstractResponse;

public class TransactionResponse  extends AbstractResponse {

	private List<Transactions> transactions;

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
	
}
