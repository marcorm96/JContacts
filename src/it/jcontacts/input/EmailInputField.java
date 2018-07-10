package it.jcontacts.input;
/**
 * Estensione della InputField che include il controllo della lunghezza del testo e se il testo inserito è un indirizzo mail 
 * @author Marco
 *
 */
public class EmailInputField extends InputField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4889373028201708119L;

	/**
	 * Costruttore
	 * @param testo testo da visualizzare
	 * @param lunghezzaMassima lunghezza massima del campo
	 */
	public EmailInputField(String testo,int lunghezzaMassima) {
		super(testo,lunghezzaMassima);		
	}

	@Override
	/**
	 * controlla l'integrità dei dati
	 * @return true se la lunghezza del testo è minore della lunghezza massima e il testo inserito è un indirizzo mail
	 */
	public boolean controllaIntegrita() {
		return super.controllaIntegrita() && verificaMail(getText());
	}

	/**
	 * Restituisce true se la mail inserita è corretta sintatticamente
	 * @param mail da verificare
	 * @return
	 */ 
	private boolean verificaMail(String mail) {		
		mail.trim();
		//controllo se la mail è vuota
		if(mail.isEmpty())
			return true;
		//controllo se è presente almeno un carattere di . e una @
		if(mail.indexOf(".")>-1 && mail.indexOf("@")>-1) 
			return true;
		return false;
	}
}
