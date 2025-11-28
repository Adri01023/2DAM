package prueba;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Persona  implements Serializable{
	
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany (cascade = CascadeType.ALL, mappedBy = "dueño")
  private Set<Email> emails = new HashSet<Email>();
  private String nombre;
  private String apellidos;
  private int edad;
  
  public void addEmail(String mail) {
	  Email email = new Email(mail);
	  email.setDueño(this);
	  emails.add(email);
  }
  
  public boolean delEmail(String strmail) {
	  Email mail = new Email(strmail);
	  return emails.remove(mail);
  }
  
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getApellidos() {
    return apellidos;
  }
  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }
  public int getEdad() {
    return edad;
  }
  public void setEdad(int edad) {
    this.edad = edad;
  }
  public Persona(String nombre, String apellidos, int edad) {
    super();
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.edad = edad;
  }
  public Persona() {
    super();
  }
public Set<Email> getEmails() {
	return emails;
}
public void setEmails(Set<Email> emails) {
	this.emails = emails;
}
  
}