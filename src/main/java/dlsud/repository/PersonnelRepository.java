package dlsud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dlsud.entity.Personnel;

@Repository
public interface PersonnelRepository extends CrudRepository<Personnel, Integer> {

	Personnel findById(int id);
}
