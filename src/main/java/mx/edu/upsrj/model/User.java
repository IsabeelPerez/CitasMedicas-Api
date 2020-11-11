package mx.edu.upsrj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.edu.upsrj.model.enums.Status;
import mx.edu.upsrj.model.enums.TypeUser;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updated_user","created_user"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;
	
	@NotEmpty
	@Size(min = 5)
	@Column(unique = true)
	private String email_user;
	
	@NotEmpty
	@Size(min = 8)
	@Column(nullable = false)
	private String password_user;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TypeUser type_user;
		
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status_user = Status.Active;
		
	@Column(nullable = false)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_user;
	
	@Column(updatable = false, nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_user;
	

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private Person person;

	
	public Long getId_user() {
		return id_user;
	}


	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}


	public String getEmail_user() {
		return email_user;
	}


	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}


	public String getPassword_user() {
		return password_user;
	}


	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}


	public TypeUser getType_user() {
		return type_user;
	}


	public void setType_user(TypeUser type_user) {
		this.type_user = type_user;
	}

	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public Status getStatus_user() {
		return status_user;
	}


	public void setStatus_user(Status status_user) {
		this.status_user = status_user;
	}


	public Date getUpdated_user() {
		return updated_user;
	}


	public void setUpdated_user(Date updated_user) {
		this.updated_user = updated_user;
	}


	public Date getCreated_user() {
		return created_user;
	}


	public void setCreated_user(Date created_user) {
		this.created_user = created_user;
	}

	
	
}
