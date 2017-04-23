package dlsud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dlsud.entity.Facilities;

@Repository
public interface FacilitiesRepository extends CrudRepository<Facilities, Integer> {

	Facilities findById(int id);
}
