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

import mx.edu.upsrj.model.enums.AreaEmployee;

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_employee","created_employee"})
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_employee;
	
	@Column
	private String number_employee;
	
	@Column
	private AreaEmployee area_employee;
	
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_employee;//java.util
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_employee;

	//Union con las tablas secundarias
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_person", referencedColumnName = "id_person")
	private Person person;

	public Long getId_employee() {
		return id_employee;
	}

	public void setId_employee(Long id_employee) {
		this.id_employee = id_employee;
	}

	public String getNumber_employee() {
		return number_employee;
	}

	public void setNumber_employee(String number_employee) {
		this.number_employee = number_employee;
	}

	public AreaEmployee getArea_employee() {
		return area_employee;
	}

	public void setArea_employee(AreaEmployee area_employee) {
		this.area_employee = area_employee;
	}

	public Date getUpdated_employee() {
		return updated_employee;
	}

	public void setUpdated_employee(Date updated_employee) {
		this.updated_employee = updated_employee;
	}

	public Date getCreated_employee() {
		return created_employee;
	}

	public void setCreated_employee(Date created_employee) {
		this.created_employee = created_employee;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	

}
