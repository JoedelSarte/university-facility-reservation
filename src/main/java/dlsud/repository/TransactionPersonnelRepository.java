package dlsud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import dlsud.entity.TransactionPersonnel;

public interface TransactionPersonnelRepository extends CrudRepository<TransactionPersonnel, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE FROM TransactionPersonnel WHERE transactionId = ?1")
	void deleteRequest(int transactionId);
	
	List<TransactionPersonnel> findByTransactionId(int transactionId);
}
