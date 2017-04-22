package dlsud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dlsud.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Transaction a SET a.isApproved = 1 WHERE a.referenceNumber=?1")
	void approveTransaction(String referenceNumber);

	@Modifying
	@Transactional
	@Query("UPDATE Transaction a SET a.isApproved = 0 WHERE a.referenceNumber=?1")
	void disapproveTransaction(String referenceNumber);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Transaction WHERE referenceNumber=?1")
	void deleteRequest(String referenceNumber);
	
	Transaction findByReferenceNumber(String referenceNumber);
	
	@Query("SELECT a FROM Transaction a WHERE MONTH(activityStartTime) = MONTH(?1) AND facilityId=?2")
	List<Transaction> findByActivityStartTimeAndFacilityId(String activityStartTime, int facilityId);
}
