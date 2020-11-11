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
import mx.edu.upsrj.model.Doctor;
import mx.edu.upsrj.model.DAO.DoctorsDAO;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DoctorsController {
	
	@Autowired
	private DoctorsDAO doctorsDAO;
	
	//Registrar
	@PostMapping("/save")
	public ResponseEntity<Response<Doctor>> saveDoctor(@Valid @RequestBody Doctor doctor, BindingResult result){
		//Validar Informacion
		Response<Doctor> response = new Response<Doctor>();
		if(result.hasErrors()) {
			response.setStatus(400);
			response.setStatus_text("Bad request");
			response.setMessage("Validation failed for Doctors. Error count: "+result.getErrorCount());
			response.setPath("/doctor/save");
			HashMap<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			response.setErrors(errors);
		}else {
			response.setStatus(200);
			response.setStatus_text("Ok");
			response.setMessage("Doctors added successfully! ");
			response.setPath("/doctor/save");
			response.setData(doctorsDAO.save(doctor));
		}
		
		return new ResponseEntity<Response<Doctor>>(response, HttpStatus.OK);
	}
	
	//Mostrar todas los datos 
	@GetMapping("see/all")
	public ResponseEntity<Response<List<Doctor>>> cargarDoctors(){
		Response<List<Doctor>> response = new Response<List<Doctor>>();
		
		List<Doctor> doctors = (List<Doctor>) doctorsDAO.findAll();
		
		response.setStatus(200);
		response.setStatus_text("Ok");
		response.setMessage("Doctors loaded successfully! ");
		response.setPath("/doctor/see/all");
		response.setData(doctors);
		return new ResponseEntity<Response<List<Doctor>>>(response, HttpStatus.OK);
	} 
	
	@GetMapping("{id}")
	public Doctor obtenerDoctorPorId(@PathVariable long id) {
		Optional<Doctor> doctor = doctorsDAO.findById(id);
		if(doctor.isPresent()) {
			return doctor.get();
		}else {
			return null;
		}
	}
	

}
