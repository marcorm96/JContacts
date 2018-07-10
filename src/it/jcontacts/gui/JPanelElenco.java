package it.jcontacts.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import it.jcontacts.managers.ContattiManager;
import it.jcontacts.models.Contatti;
/**
 * Pannello contenente gli elementi atti a gestire l'elenco
 * @author Marco
 *
 */
public class JPanelElenco extends JPanel {		

	/**
	 * 
	 */
	private static final long serialVersionUID = -5629900551181336146L;

	/**
	 * Matrice contenente sulle righe i diversi contatti da visualizzare all'interno della tabella
	 */
	private Object[][] contatti;	
	/**
	 * Tabella in cui verranno visualizzati i contatti
	 */
	private JTable contattiTable;
	/**
	 * Pannello scrollabile contenente la tabella dei contatti
	 */
	private JScrollPane scrollPane;
	
	public JPanelElenco() {
		//imposto il layout
		setLayout(new BorderLayout());
		//Pannello contenente i bottoni per le operazioni di NUOVO,MODIFICA,ELIMINA
		JPanel jpanelOperazioni=new JPanel();				

		JButton nuovoButton=new JButton("Nuovo");
		nuovoButton.addActionListener(e -> JFrameJContacts.cambiaPanel(new JPanelDettaglio(new Contatti()), this));
		jpanelOperazioni.add(nuovoButton);

		JButton eliminaButton=new JButton("Elimina");
		eliminaButton.addActionListener(e -> {
			try {
				//verifico se è stato selezionato un elemento 
				if(contattiTable.getSelectedRow()==-1) JOptionPane.showMessageDialog(null, "Selezionare un elemento da eliminare");
				else {
					if(JOptionPane.showConfirmDialog(null, "Procedere con l'eliminazione?","Eliminazione Contatto",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						int idSelezionato=(Integer) contattiTable.getModel().getValueAt(contattiTable.getSelectedRow(), 0);				
						ContattiManager.getInstance().elimina(idSelezionato);						
					}
					JFrameJContacts.cambiaPanel(new JPanelElenco(), this);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Errore nell'eliminazione del contatto:"+e1.getMessage());
				e1.printStackTrace();
			}
		});

		jpanelOperazioni.add(eliminaButton);

		JButton modificaButton=new JButton("Modifica");
		modificaButton.addActionListener(e -> {
			try {
				//verifico se è stato selezionato un elemento
				if(contattiTable.getSelectedRow()==-1) JOptionPane.showMessageDialog(null, "Selezionare un elemento da modificare");
				else {
					int idSelezionato=(Integer) contattiTable.getModel().getValueAt(contattiTable.getSelectedRow(), 0);				
					Contatti c=ContattiManager.getInstance().leggi(idSelezionato);
					JFrameJContacts.cambiaPanel(new JPanelDettaglio(c), this);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Errore nella modifica del contatto:"+e1.getMessage());
				e1.printStackTrace();
			}
		});

		jpanelOperazioni.add(modificaButton);		
		add(jpanelOperazioni,BorderLayout.NORTH);		
		//Pannello contentente il filtro per la ricerca
		JPanel panelRicerca=new JPanel();
		panelRicerca.setLayout(new GridLayout(1, 2));
		panelRicerca.add(new JLabel("Ricerca:"));
		
		JTextField ricercaTF=new JTextField();
		//ogni volta che il testo viene modificato,ricarico l'elenco dei contatti in base al testo 
		ricercaTF.getDocument().addDocumentListener(new DocumentListener() {
			
			private void update() {				
				remove(scrollPane);
				ricaricaTabella(ricercaTF.getText());
				revalidate();
			}
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				update();				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				update();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				update();				
			}
		});
		
		panelRicerca.add(ricercaTF);
		add(panelRicerca,BorderLayout.CENTER);
		
		ricaricaTabella("");
		
		
	}

	/**
	 * Ricarica l'elenco dei contatti in base all'indizio passato e trasforma la lista ottenuta in una matrice da inserire nella tabella
	 * @param indizio filtro di ricerca
	 */
	private void ricaricaTabella(String indizio) {
		//leggo l'elenco dei contatti dal db
		List<Contatti> elencoContatti=ContattiManager.getInstance().elenco(indizio);
		contatti=new Object[elencoContatti.size()][6];		
		for(int i=0;i<elencoContatti.size();i++) {
			contatti[i][0]=elencoContatti.get(i).getIdContatto();
			contatti[i][1]=elencoContatti.get(i).getNome();
			contatti[i][2]=elencoContatti.get(i).getCognome();
			contatti[i][3]=elencoContatti.get(i).getEmail();
			contatti[i][4]=elencoContatti.get(i).getIndirizzo();
			contatti[i][5]=elencoContatti.get(i).getTelefono();
		}
		//ottengo i nomi delle colonne con i nomi dei campi della classe Contatti		
		String[] nomiColonne=new String[6];
		for(int i=0;i<6;i++) {
			String nome=Contatti.class.getDeclaredFields()[i+1].getName();			
			nomiColonne[i]=nome.substring(0, 1).toUpperCase() + nome.substring(1);
		}
		//creo la tabella
		contattiTable=new JTable(contatti,nomiColonne);
		//imposto il vincolo di selezionare solo i singoli elementi
		contattiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		contattiTable.setFillsViewportHeight(true);
		//rimuovo la colonna contenente l'id in modo che non venga visualizzato
		contattiTable.getColumnModel().removeColumn(contattiTable.getColumn(nomiColonne[0]));
		//inserisco la tabella nell'elenco scrollabile
		scrollPane=new JScrollPane(contattiTable);
		scrollPane.setPreferredSize(new Dimension(480, 100));
		add(scrollPane,BorderLayout.SOUTH);
	}			
	
}
