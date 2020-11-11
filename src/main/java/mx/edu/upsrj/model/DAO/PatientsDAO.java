package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Patient;

@Repository
public interface PatientsDAO extends CrudRepository<Patient, Long> {

}
