package dlsud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dlsud.entity.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {

}
