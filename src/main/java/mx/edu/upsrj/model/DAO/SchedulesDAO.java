package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Schedule;

@Repository
public interface SchedulesDAO extends CrudRepository<Schedule, Long>{

}
