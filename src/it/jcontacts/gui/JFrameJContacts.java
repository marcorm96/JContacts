package it.jcontacts.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Frame principale dell'applicazione
 * Usa il Singleton Pattern
 * @author Marco
 *
 */
public class JFrameJContacts extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8317304024076686984L;

	private static JFrameJContacts instance;

	public static JFrameJContacts getInstance() {
		if(instance==null) instance=new JFrameJContacts();
		return instance;
	}

	private JFrameJContacts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("JContacts");				
		getContentPane().add(new JPanelElenco());
		//setto le dimensioni
		setSize(new Dimension(480,200));
		//posiziono la finestra al centro dello schermo
		setLocationRelativeTo(null);				
		//rendo visibile la finestra
		setVisible(true);
	}

	/**
	 * Carica e visualizza il panelDestinazione ed elimina dal Frame il panelSorgente
	 * @param panelDestinazione pannello da caricare e visualizzare
	 * @param panelSorgente pannello di provenienza
	 */
	public static void cambiaPanel(JPanel panelDestinazione,JPanel panelSorgente) {
		getInstance().getContentPane().add(panelDestinazione);
		getInstance().getContentPane().remove(panelSorgente);		
		getInstance().revalidate();		
	}
}
