package mx.edu.upsrj.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "patients")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_patient","created_patient"})
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_patient;
	
	@Column
	private String diseases_patient;
	
	@Column
	private String allergies_patient;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_patient;//java.util
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_patient;

	//Union con las tablas secundarias
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_person", referencedColumnName = "id_person")
	private Person person;
	
	//Getters and Setters
	public Date getUpdated_patient() {
		return updated_patient;
	}

	public void setUpdated_patient(Date updated_patient) {
		this.updated_patient = updated_patient;
	}

	public Date getCreated_patient() {
		return created_patient;
	}

	public void setCreated_patient(Date created_patient) {
		this.created_patient = created_patient;
	}

	public Long getId_patient() {
		return id_patient;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setId_patient(Long id_patient) {
		this.id_patient = id_patient;
	}

	public String getDiseases_patient() {
		return diseases_patient;
	}

	public void setDiseases_patient(String diseases_patient) {
		this.diseases_patient = diseases_patient;
	}

	public String getAllergies_patient() {
		return allergies_patient;
	}

	public void setAllergies_patient(String allergies_patient) {
		this.allergies_patient = allergies_patient;
	}


	
	
	
	

}
