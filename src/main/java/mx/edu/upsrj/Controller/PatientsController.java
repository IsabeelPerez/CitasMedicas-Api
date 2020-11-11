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
import mx.edu.upsrj.model.Patient;
import mx.edu.upsrj.model.DAO.PatientsDAO;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PatientsController {
	
	@Autowired
	private PatientsDAO patientsDAO;
	
	//Registrar
		@PostMapping("/save")
		public ResponseEntity<Response<Patient>> savePatient(@Valid @RequestBody Patient patient, BindingResult result){
			//Validar Informacion
			Response<Patient> response = new Response<Patient>();
			if(result.hasErrors()) {
				response.setStatus(400);
				response.setStatus_text("Bad request");
				response.setMessage("Validation failed for Patients. Error count: "+result.getErrorCount());
				response.setPath("/patient/save");
				HashMap<String, String> errors = new HashMap<>();
				result.getFieldErrors().forEach(error -> {
					errors.put(error.getField(), error.getDefaultMessage());
				});
				response.setErrors(errors);
			}else {
				response.setStatus(200);
				response.setStatus_text("Ok");
				response.setMessage("Patients added successfully! ");
				response.setPath("/patient/save");
				response.setData(patientsDAO.save(patient));
			}
			
			return new ResponseEntity<Response<Patient>>(response, HttpStatus.OK);
		}
		
		//Mostrar todas los datos 
		@GetMapping("see/all")
		public ResponseEntity<Response<List<Patient>>> cargarPatients(){
			Response<List<Patient>> response = new Response<List<Patient>>();
			
			List<Patient> patients = (List<Patient>) patientsDAO.findAll();
			
			response.setStatus(200);
			response.setStatus_text("Ok");
			response.setMessage("Patients loaded successfully! ");
			response.setPath("/patient/see/all");
			response.setData(patients);
			return new ResponseEntity<Response<List<Patient>>>(response, HttpStatus.OK);
		} 
		
		@GetMapping("{id}")
		public Patient obtenerPatientPorId(@PathVariable long id) {
			Optional<Patient> patient = patientsDAO.findById(id);
			if(patient.isPresent()) {
				return patient.get();
			}else {
				return null;
			}
		}
		

}
