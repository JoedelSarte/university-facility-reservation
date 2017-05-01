package dlsud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dlsud.entity.TransactionEquipment;

@Repository
public interface TransactionEquipmentRepository extends CrudRepository<TransactionEquipment, Integer> {

	@Modifying
	@Transactional
	@Query("DELETE FROM TransactionEquipment WHERE transactionId = ?1")
	void deleteRequest(int transactionId);
	
	List<TransactionEquipment> findByTransactionId(int transactionId);
}
