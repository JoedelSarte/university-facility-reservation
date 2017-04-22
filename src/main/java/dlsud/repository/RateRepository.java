package dlsud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dlsud.entity.Rate;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {
	
	Rate findByFacilityIdAndUserTypeId(int facilityId, int userTypeId);

}
