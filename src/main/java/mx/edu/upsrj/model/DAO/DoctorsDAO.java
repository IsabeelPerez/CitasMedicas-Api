package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Doctor;

@Repository
public interface DoctorsDAO extends CrudRepository<Doctor, Long> {


}
