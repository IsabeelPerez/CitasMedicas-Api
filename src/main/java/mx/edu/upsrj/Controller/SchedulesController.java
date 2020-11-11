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
import mx.edu.upsrj.model.Schedule;
import mx.edu.upsrj.model.DAO.SchedulesDAO;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class SchedulesController {
	
	@Autowired
	private SchedulesDAO schedulesDAO;
	
	//Read
			@GetMapping
			public ResponseEntity<Response<List<Schedule>>> listarSchedules(){
				Response<List<Schedule>> response = new Response<List<Schedule>>();
				
				List<Schedule> schedule = (List<Schedule>) schedulesDAO.findAll();
				
				response.setStatus(200);
				response.setStatus_text("Ok");
				response.setMessage("Specialities loaded successfully! ");
				response.setPath("/api/schedules");
				response.setData(schedule);
				
				return new ResponseEntity<Response<List<Schedule>>>(response, HttpStatus.OK);
				
			}
			@GetMapping("{id}")
			public Schedule obtenerSchedulePorId(@PathVariable long id) {
				Optional<Schedule> schedule = schedulesDAO.findById(id);
				if(schedule.isPresent()) {
					return schedule.get();
				}else {
					return null;
				}
			}
			
			//create
			@PostMapping
			public ResponseEntity<Response<Schedule>> registrarSchedule(@Valid @RequestBody Schedule schedule, BindingResult result) {
				//validar la informacion
				Response<Schedule> response = new Response<>();
				if(result.hasErrors()) {
					response.setStatus(400);
					response.setStatus_text("Bad request");
					response.setMessage("Validation failed for schedules. Error count: "+result.getErrorCount());
					response.setPath("api/schedules");
					HashMap<String, String> errors = new HashMap<>();
					result.getFieldErrors().forEach(error -> {
						errors.put(error.getField(), error.getDefaultMessage());
					});
					response.setErrors(errors);
				}else {
					response.setStatus(200);
					response.setStatus_text("Ok");
					response.setMessage("Schedule added successfully! ");
					response.setPath("api/schedules");
					response.setData(schedulesDAO.save(schedule));
					
				}
				return new ResponseEntity<Response<Schedule>>(response,HttpStatus.OK);
			}
			
			//Update
			@PutMapping("{id}")
			public Schedule modificarSchedule(@PathVariable long id, @RequestBody Schedule schedule) {
				Optional<Schedule> schedule_update = schedulesDAO.findById(id);
				if(schedule_update.isPresent()) {
					schedule.setId_schedule(schedule_update.get().getId_schedule());
					return schedulesDAO.save(schedule);
				}else {
					return null;
				}
			}
			
			//Delete
			@DeleteMapping("{id}")
			public Schedule eliminarSchedule(@PathVariable long id) {
				Optional<Schedule> schedule_delete = schedulesDAO.findById(id);
				if(schedule_delete.isPresent()) {
					schedulesDAO.delete(schedule_delete.get());
					return schedule_delete.get();
				}else {
					return null;
				}
			}


}
