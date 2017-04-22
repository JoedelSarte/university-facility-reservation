package dlsud.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import dlsud.entity.TransactionDetails;

public interface TransactionDetailsRepository extends CrudRepository<TransactionDetails, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE FROM TransactionDetails WHERE transactionId = ?1")
	void deleteRequest(int transactionId);
}
