package it.jcontacts.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.jcontacts.input.EmailInputField;
import it.jcontacts.input.InputField;
import it.jcontacts.input.NumericInputField;
import it.jcontacts.managers.ContattiManager;
import it.jcontacts.models.Contatti;
/**
 * Pannello contenente una serie di campi da utilizzare per creare o modificare un contatto
 * @author Marco
 *
 */
public class JPanelDettaglio extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1485922082384067734L;

	/**
	 * Array contenente gli inputFields da visualizzare
	 */
	private InputField[] inputFields;	

	public JPanelDettaglio(Contatti contatto) {		
		//imposto il layout
		setLayout(new GridLayout(6, 2));
		JButton salvaButton=new JButton("Salva");
		/* bottone che verifica se sono stati inseriti i dati
		 * nei campi obbligatori e procede al salvataggio sul db
		 */
		salvaButton.addActionListener( e -> {
			for(InputField i:inputFields) {
				if(!i.controllaIntegrita()) {
					JOptionPane.showMessageDialog(null, "Verificare i campi inseriti");
					return;
				}
			}
			if(inputFields[0].getText().isEmpty() || inputFields[1].getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Compilare i campi obbligatori");
				return;
			}			
			//riempio i campi del contatto in base al contenuto degli inputFields
			contatto.setNome(inputFields[0].getText());
			contatto.setCognome(inputFields[1].getText());
			contatto.setEmail(inputFields[2].getText());
			contatto.setIndirizzo(inputFields[3].getText());
			contatto.setTelefono(inputFields[4].getText());
			try {
				ContattiManager.getInstance().salva(contatto);
				JOptionPane.showMessageDialog(null, "Salvataggio effettuato con successo");
				JFrameJContacts.cambiaPanel(new JPanelElenco(), this);				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Errore nel salvataggio del contatto:"+e1.getMessage());
			}							
		});

		add(salvaButton);
		JButton annullaButton=new JButton("Annulla");
		//bottone che annulla l'operazione e ritorna all'elenco
		annullaButton.addActionListener(e -> JFrameJContacts.cambiaPanel(new JPanelElenco(), this));
		add(annullaButton);		
		//creo gli inputFields contenenti i dati del contatto passato
		inputFields=new InputField[] { new InputField(contatto.getNome(),128), new InputField(contatto.getCognome(),128), new EmailInputField(contatto.getEmail(),256), new InputField(contatto.getIndirizzo(),1024), new NumericInputField(contatto.getTelefono(),10)};		
		for(int i=0;i<inputFields.length;i++) {			
			//aggiungo le etichette e gli inputFields al pannello
			String etichetta=Contatti.class.getDeclaredFields()[i+2].getName();			
			etichetta=etichetta.substring(0, 1).toUpperCase() + etichetta.substring(1);
			if(i<2) etichetta+="*";
			add(new JLabel(etichetta+":"));			
			add(inputFields[i]);
		}
	}
	
}
