package it.jcontacts.input;

import javax.swing.JTextField;
/**
 * Estensione della JTextField che include il controllo sulla lunghezza del campo di testo
 * @author Marco
 *
 */
public class InputField extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4267396586441050064L;
	/**
	 * lunghezza massima del testo
	 */
	private int lunghezzaMassima;	
	
	/**
	 * Costruttore
	 * @param testo testo da visualizzare
	 * @param lunghezzaMassima lunghezza massima del campo
	 */
	public InputField(String testo,int lunghezzaMassima) {
		super(testo);
		this.lunghezzaMassima=lunghezzaMassima;
	}

	/**
	 * controlla l'integrità dei dati
	 * @return true se la lunghezza del testo è minore della lunghezza massima
	 */
	public boolean controllaIntegrita() {
		return getText().length()<=lunghezzaMassima;
	}
}
