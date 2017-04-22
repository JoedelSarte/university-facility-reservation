package dlsud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dlsud.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	User findByIdNumber(String idNumber);
	User findById(int id);
}
