package it.jcontacts.input;
/**
 * Estensione della InputField che include il controllo della lunghezza del testo e se effettivamente è stato inserito un numero 
 * @author Marco
 *
 */
public class NumericInputField extends InputField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1349839252461791576L;

	/**
	 * Costruttore
	 * @param testo testo da visualizzare
	 * @param lunghezzaMassima lunghezza massima del campo
	 */
	public NumericInputField(String testo,int lunghezzaMassima) {
		super(testo,lunghezzaMassima);		
	}
	
	
	@Override
	/**
	 * controlla l'integrità dei dati
	 * @return true se la lunghezza del testo è minore della lunghezza massima e il testo inserito è un numero
	 */
	public boolean controllaIntegrita() {	
		if(super.controllaIntegrita()) {
			try {
				if(getText().isEmpty()) return true;
				Long.parseLong(getText());
				return true;
			}catch (Exception e) {
				
			}
		}
		return false;
	}
	
}
