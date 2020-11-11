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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.upsrj.helper.Response;
import mx.edu.upsrj.model.Speciality;
import mx.edu.upsrj.model.DAO.SpecialitiesDAO;

@RestController
@RequestMapping("/api/speialities")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SpecialitiesController {

	@Autowired
	private SpecialitiesDAO specialitiesDAO;
	
	//Read
		@GetMapping
		public ResponseEntity<Response<List<Speciality>>> listarSpecialities(){
			Response<List<Speciality>> response = new Response<List<Speciality>>();
			
			List<Speciality> speciality = (List<Speciality>) specialitiesDAO.findAll();
			
			response.setStatus(200);
			response.setStatus_text("Ok");
			response.setMessage("Specialities loaded successfully! ");
			response.setPath("/api/speialities");
			response.setData(speciality);
			
			return new ResponseEntity<Response<List<Speciality>>>(response, HttpStatus.OK);
			
		}

		@GetMapping("{id}")
		public Speciality obtenerSpecialityPorId(@PathVariable long id) {
			Optional<Speciality> speciality = specialitiesDAO.findById(id);
			if(speciality.isPresent()) {
				return speciality.get();
			}else {
				return null;
			}
		}
		
		//create
		@PostMapping
		public ResponseEntity<Response<Speciality>> registrarSpeciality(@Valid @RequestBody Speciality speciality, BindingResult result) {
			//validar la informacion
			Response<Speciality> response = new Response<>();
			if(result.hasErrors()) {
				response.setStatus(400);
				response.setStatus_text("Bad request");
				response.setMessage("Validation failed for Specialities. Error count: "+result.getErrorCount());
				response.setPath("api/specialities");
				HashMap<String, String> errors = new HashMap<>();
				result.getFieldErrors().forEach(error -> {
					errors.put(error.getField(), error.getDefaultMessage());
				});
				response.setErrors(errors);
			}else {
				response.setStatus(200);
				response.setStatus_text("Ok");
				response.setMessage("Speciality added successfully! ");
				response.setPath("api/specialities");
				response.setData(specialitiesDAO.save(speciality));
				
			}
			return new ResponseEntity<Response<Speciality>>(response,HttpStatus.OK);
		}
		
		//Update
		@PutMapping("{id}")
		public Speciality modificarSpeciality(@PathVariable long id, @RequestBody Speciality speciality) {
			Optional<Speciality> speciality_update = specialitiesDAO.findById(id);
			if(speciality_update.isPresent()) {
				speciality.setId_speciality(speciality_update.get().getId_speciality());
				return specialitiesDAO.save(speciality);
			}else {
				return null;
			}
		}
		
		//Delete
		@DeleteMapping("{id}")
		public Speciality eliminarSpeciality(@PathVariable long id) {
			Optional<Speciality> speciality_delete = specialitiesDAO.findById(id);
			if(speciality_delete.isPresent()) {
				specialitiesDAO.delete(speciality_delete.get());
				return speciality_delete.get();
			}else {
				return null;
			}
		}
		
}
