package mx.edu.upsrj.model.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.upsrj.model.Employee;

@Repository
public interface EmployeesDAO extends CrudRepository<Employee, Long>{

}
