package prueba;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Email {
	public Email(String mail) {
		this.mail = mail;
	}
	
	public Email() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String mail;
	@ManyToOne
	Persona dueño;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Persona getDueño() {
		return dueño;
	}

	public void setDueño(Persona dueño) {
		this.dueño = dueño;
	}

	@Override
	public String toString() {
		return getMail();
	}
	
}
