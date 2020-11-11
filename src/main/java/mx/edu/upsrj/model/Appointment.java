package mx.edu.upsrj.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Status;
import mx.edu.upsrj.model.enums.StatusDone;

@Entity
@Table(name = "appointment")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_appointment","created_appointment"})
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_appointment;
	
	@Column
	private Date date_appointment;
	
	@Column
	private Time hora_inicio;
	
	@Column
	private Time hora_fin;
	
	@JsonIgnore
	@ManyToOne
	private Doctor doctor;
	
	@JsonIgnore
	@ManyToOne
	private Schedule schedule;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private StatusDone status_attendance = StatusDone.False;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private StatusDone status_ayment = StatusDone.False;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_appointment = Status.Active;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_appointment;
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_appointment;

	public long getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(long id_appointment) {
		this.id_appointment = id_appointment;
	}

	public Date getDate_appointment() {
		return date_appointment;
	}

	public void setDate_appointment(Date date_appointment) {
		this.date_appointment = date_appointment;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Time getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public StatusDone getStatus_attendance() {
		return status_attendance;
	}

	public void setStatus_attendance(StatusDone status_attendance) {
		this.status_attendance = status_attendance;
	}

	public StatusDone getStatus_ayment() {
		return status_ayment;
	}

	public void setStatus_ayment(StatusDone status_ayment) {
		this.status_ayment = status_ayment;
	}

	public Status getStatus_appointment() {
		return status_appointment;
	}

	public void setStatus_appointment(Status status_appointment) {
		this.status_appointment = status_appointment;
	}

	public Date getUpdated_appointment() {
		return updated_appointment;
	}

	public void setUpdated_appointment(Date updated_appointment) {
		this.updated_appointment = updated_appointment;
	}

	public Date getCreated_appointment() {
		return created_appointment;
	}

	public void setCreated_appointment(Date created_appointment) {
		this.created_appointment = created_appointment;
	}
	

}
