package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Speciality;

@Repository
public interface SpecialitiesDAO extends CrudRepository<Speciality, Long>{

}
