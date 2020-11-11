package mx.edu.upsrj.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Status;

@Entity
@Table(name = "doctors")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_doctor","created_doctor"})
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_doctor;
	
	@NotEmpty
	@Size(min = 5, max = 80)
	@Column(nullable = false, length = 80)
	private String cedula_doctor;
	
	/*@NotEmpty
	@Column(nullable = false, length = 80)
	private String speciality_doctor;*/
	
	/*@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_speciality", nullable = false)
	private Speciality speciality;*/

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_doctor = Status.Active;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_doctor;
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_doctor;
	
	//Union con las tablas secundarias
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_person", referencedColumnName = "id_person")
	private Person person;
	
	/*@JsonIgnore
	@ManyToOne
	private Speciality speciality;*/
	
	@OneToMany(mappedBy = "doctor")
	//@JoinColumn(name = "speciality")
	List<Schedule> schecule;
	
	@OneToMany(mappedBy = "doctor")
	//@JoinColumn(name = "speciality")
	List<Appointment> appointment;
	
	//Getters and Setters


	public List<Schedule> getSchecule() {
		return schecule;
	}

	public void setSchecule(List<Schedule> schecule) {
		this.schecule = schecule;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public Long getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(Long id_doctor) {
		this.id_doctor = id_doctor;
	}

	public String getCedula_doctor() {
		return cedula_doctor;
	}

	public void setCedula_doctor(String cedula_doctor) {
		this.cedula_doctor = cedula_doctor;
	}

	/*public String getEspecialidad_doctor() {
		return speciality_doctor;
	}

	public void setEspecialidad_doctor(String especialidad_doctor) {
		this.speciality_doctor = especialidad_doctor;
	}*/

	public Status getStatus_doctor() {
		return status_doctor;
	}

	public void setStatus_doctor(Status status_doctor) {
		this.status_doctor = status_doctor;
	}

	public Date getUpdated_doctor() {
		return updated_doctor;
	}

	public void setUpdated_doctor(Date updated_doctor) {
		this.updated_doctor = updated_doctor;
	}

	public Date getCreated_doctor() {
		return created_doctor;
	}

	public void setCreated_doctor(Date created_doctor) {
		this.created_doctor = created_doctor;
	}

	/*public String getSpeciality_doctor() {
		return speciality_doctor;
	}

	public void setSpeciality_doctor(String speciality_doctor) {
		this.speciality_doctor = speciality_doctor;
	}*/

	public Person getPerson() {
		return person;
	}

	/*public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}*/

	public void setPerson(Person person) {
		this.person = person;
	}

	
}
