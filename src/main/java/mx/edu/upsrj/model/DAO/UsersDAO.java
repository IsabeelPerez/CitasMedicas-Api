package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.User;


@Repository
public interface UsersDAO extends CrudRepository<User, Long>{

}
