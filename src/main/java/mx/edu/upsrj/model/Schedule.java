package mx.edu.upsrj.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Day;
import mx.edu.upsrj.model.enums.ScheduleType;
import mx.edu.upsrj.model.enums.Status;

@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_schedule","created_schedule"})
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_schedule;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Day day;
	
	@Column
	private Time hora_inicio;
	
	@Column
	private Time hora_fin;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ScheduleType scheduleType;
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_schedule = Status.Active;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_schedule;
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_schedule;
	
	@JsonIgnore
	@ManyToOne
	private Speciality speciality;
	
	@JsonIgnore
	@ManyToOne
	private Doctor doctor;
	
	@OneToMany(mappedBy = "schedule")
	//@JoinColumn(name = "speciality")
	List<Appointment> appointment;

	public long getId_schedule() {
		return id_schedule;
	}

	public void setId_schedule(long id_schedule) {
		this.id_schedule = id_schedule;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
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

	public ScheduleType getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Status getStatus_schedule() {
		return status_schedule;
	}

	public void setStatus_schedule(Status status_schedule) {
		this.status_schedule = status_schedule;
	}

	public Date getUpdated_schedule() {
		return updated_schedule;
	}

	public void setUpdated_schedule(Date updated_schedule) {
		this.updated_schedule = updated_schedule;
	}

	public Date getCreated_schedule() {
		return created_schedule;
	}

	public void setCreated_schedule(Date created_schedule) {
		this.created_schedule = created_schedule;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
}
