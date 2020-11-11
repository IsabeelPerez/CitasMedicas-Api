package mx.edu.upsrj.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Status;

@Entity
@Table(name = "specialities")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_speciality","created_speciality"})
public class Speciality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_speciality;
	
	@Column
	private String nombre_speciality;
	
	public Status getStatus_speciality() {
		return status_speciality;
	}

	public void setStatus_speciality(Status status_speciality) {
		this.status_speciality = status_speciality;
	}

	/*public List<Doctor> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}*/

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_speciality = Status.Active;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_speciality;
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_speciality;
	
	@OneToMany(mappedBy = "speciality")
	//@JoinColumn(name = "speciality")
	List<Schedule> schecule;
	

	/*@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_doctor", referencedColumnName = "id_doctor")
	private Doctor doctor;*/

	public long getId_speciality() {
		return id_speciality;
	}

	public void setId_speciality(long id_speciality) {
		this.id_speciality = id_speciality;
	}

	public String getNombre_speciality() {
		return nombre_speciality;
	}

	public void setNombre_speciality(String nombre_speciality) {
		this.nombre_speciality = nombre_speciality;
	}

	public Date getUpdated_speciality() {
		return updated_speciality;
	}

	public void setUpdated_speciality(Date updated_speciality) {
		this.updated_speciality = updated_speciality;
	}

	public Date getCreated_speciality() {
		return created_speciality;
	}

	public void setCreated_speciality(Date created_speciality) {
		this.created_speciality = created_speciality;
	}

	
}
