package mx.edu.upsrj.model;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Genders;
import mx.edu.upsrj.model.enums.Status;

@Entity
@Table(name = "persons")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_person","created_person"})
public class Person {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_person;
	
	@NotEmpty
	@Size(min = 4, max = 80)
	@Column(nullable = false, length = 80)
	private String name_person;
	
	@NotEmpty
	@Size(min = 4, max = 80)
	@Column(nullable = false, length = 80)
	private String lastnames_person;

	@NotNull
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Genders gender_person;
	
	@NotNull
	@Column(length = 20)
	private Date birthdate_person;
	
	@NotNull
	@Size(min = 17, max = 19)
	@Column(length = 18)
	private String curp_person;
	
	@NotEmpty
	@Size(min = 10, max = 20)
	@Column(length = 20)
	private String phone_person;
		
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_person = Status.Active;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_person;//java.util
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_person;
	
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user", referencedColumnName = "id_user")
	private User user;
	
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Doctor doctor;
	
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Patient patient;
	
	@JsonIgnore
	@OneToOne(mappedBy = "person")
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Long getId_person() {
		return id_person;
	}

	public void setId_person(Long id_person) {
		this.id_person = id_person;
	}

	public String getName_person() {
		return name_person;
	}

	public void setName_person(String name_person) {
		this.name_person = name_person;
	}

	public String getLastnames_person() {
		return lastnames_person;
	}

	public void setLastnames_person(String lastnames_person) {
		this.lastnames_person = lastnames_person;
	}

	public Genders getGender_person() {
		return gender_person;
	}

	public void setGender_person(Genders gender_person) {
		this.gender_person = gender_person;
	}

	public Date getBirthdate_person() {
		return birthdate_person;
	}

	public void setBirthdate_person(Date birthdate_person) {
		this.birthdate_person = birthdate_person;
	}

	public String getCurp_person() {
		return curp_person;
	}

	public void setCurp_person(String curp_person) {
		this.curp_person = curp_person;
	}

	public String getPhone_person() {
		return phone_person;
	}

	public void setPhone_person(String phone_person) {
		this.phone_person = phone_person;
	}

	public Status getStatus_person() {
		return status_person;
	}

	public void setStatus_person(Status status_person) {
		this.status_person = status_person;
	}

	public Date getUpdated_person() {
		return updated_person;
	}

	public void setUpdated_person(Date updated_person) {
		this.updated_person = updated_person;
	}

	public Date getCreated_person() {
		return created_person;
	}

	public void setCreated_person(Date created_person) {
		this.created_person = created_person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
