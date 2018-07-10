package it.jcontacts.models;
// Generated 5-lug-2018 17.34.30 by Hibernate Tools 5.3.1.Final

/**
 * Modello della tabella Contatti
 */
public class Contatti implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326869564647784472L;
	private Integer idContatto;
	private String nome;
	private String cognome;
	private String email;
	private String indirizzo;
	private String telefono;

	public Contatti() {
	}	
	
	public Contatti(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	public Contatti(String nome, String cognome, String email, String indirizzo, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
	}

	public Integer getIdContatto() {
		return this.idContatto;
	}

	public void setIdContatto(Integer idContatto) {
		this.idContatto = idContatto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
