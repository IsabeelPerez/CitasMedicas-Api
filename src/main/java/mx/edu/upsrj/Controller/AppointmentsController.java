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
import mx.edu.upsrj.model.Appointment;
import mx.edu.upsrj.model.DAO.AppointmentsDAO;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AppointmentsController {
	
	@Autowired
	private AppointmentsDAO appointmentsDAO;
	
	//Read
			@GetMapping
			public ResponseEntity<Response<List<Appointment>>> listarAppointments(){
				Response<List<Appointment>> response = new Response<List<Appointment>>();
				
				List<Appointment> appointment = (List<Appointment>) appointmentsDAO.findAll();
				
				response.setStatus(200);
				response.setStatus_text("Ok");
				response.setMessage("Appointments loaded successfully! ");
				response.setPath("/api/appointments");
				response.setData(appointment);
				
				return new ResponseEntity<Response<List<Appointment>>>(response, HttpStatus.OK);
				
			}
			
			@GetMapping("{id}")
			public Appointment obtenerAppointmentPorId(@PathVariable long id) {
				Optional<Appointment> appointment = appointmentsDAO.findById(id);
				if(appointment.isPresent()) {
					return appointment.get();
				}else {
					return null;
				}
			}
			
			//create
			@PostMapping
			public ResponseEntity<Response<Appointment>> registrarAppointment(@Valid @RequestBody Appointment appointment, BindingResult result) {
				//validar la informacion
				Response<Appointment> response = new Response<>();
				if(result.hasErrors()) {
					response.setStatus(400);
					response.setStatus_text("Bad request");
					response.setMessage("Validation failed for Specialities. Error count: "+result.getErrorCount());
					response.setPath("/api/appointments");
					HashMap<String, String> errors = new HashMap<>();
					result.getFieldErrors().forEach(error -> {
						errors.put(error.getField(), error.getDefaultMessage());
					});
					response.setErrors(errors);
				}else {
					response.setStatus(200);
					response.setStatus_text("Ok");
					response.setMessage("Appointment added successfully! ");
					response.setPath("/api/appointments");
					response.setData(appointmentsDAO.save(appointment));
					
				}
				return new ResponseEntity<Response<Appointment>>(response,HttpStatus.OK);
			}
			
			//Update
			@PutMapping("{id}")
			public Appointment modificarAppointment(@PathVariable long id, @RequestBody Appointment appointment) {
				Optional<Appointment> appointment_update = appointmentsDAO.findById(id);
				if(appointment_update.isPresent()) {
					appointment.setId_appointment(appointment_update.get().getId_appointment());
					return appointmentsDAO.save(appointment);
				}else {
					return null;
				}
			}
			
			//Delete
			@DeleteMapping("{id}")
			public Appointment eliminarAppointment(@PathVariable long id) {
				Optional<Appointment> appointment_delete = appointmentsDAO.findById(id);
				if(appointment_delete.isPresent()) {
					appointmentsDAO.delete(appointment_delete.get());
					return appointment_delete.get();
				}else {
					return null;
				}
			}

}
