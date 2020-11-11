package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Appointment;

@Repository
public interface AppointmentsDAO extends CrudRepository<Appointment, Long> {

}
