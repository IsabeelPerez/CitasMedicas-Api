package mx.edu.upsrj.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.upsrj.helper.Response;
import mx.edu.upsrj.model.Employee;
import mx.edu.upsrj.model.DAO.EmployeesDAO;


@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EmployeesController {

	@Autowired
	private EmployeesDAO employeesDAO;
	
	//Registrar
			@PostMapping("/save")
			public ResponseEntity<Response<Employee>> savePerson(@Valid @RequestBody Employee employee, BindingResult result){
				//Validar Informacion
				Response<Employee> response = new Response<Employee>();
				if(result.hasErrors()) {
					response.setStatus(400);
					response.setStatus_text("Bad request");
					response.setMessage("Validation failed for Patients. Error count: "+result.getErrorCount());
					response.setPath("/person/save");
					HashMap<String, String> errors = new HashMap<>();
					result.getFieldErrors().forEach(error -> {
						errors.put(error.getField(), error.getDefaultMessage());
					});
					response.setErrors(errors);
				}else {
					response.setStatus(200);
					response.setStatus_text("Ok");
					response.setMessage("Employee added successfully! ");
					response.setPath("/employee/save");
					response.setData(employeesDAO.save(employee));
				}
				
				return new ResponseEntity<Response<Employee>>(response, HttpStatus.OK);
			}
			
			//Mostrar todas los datos 
			@GetMapping("see/all")
			public ResponseEntity<Response<List<Employee>>> cargarPatients(){
				Response<List<Employee>> response = new Response<List<Employee>>();
				
				List<Employee> employees = (List<Employee>) employeesDAO.findAll();
				
				response.setStatus(200);
				response.setStatus_text("Ok");
				response.setMessage("Employees loaded successfully! ");
				response.setPath("/employee/see/all");
				response.setData(employees);
				return new ResponseEntity<Response<List<Employee>>>(response, HttpStatus.OK);
			} 
			
			@GetMapping("{id}")
			public Employee obtenerEmployeePorId(@PathVariable long id) {
				Optional<Employee> employee = employeesDAO.findById(id);
				if(employee.isPresent()) {
					return employee.get();
				}else {
					return null;
				}
			}
			 
			
	
}
