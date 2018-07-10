package it.jcontacts.managers;

import java.util.List;

import org.hibernate.Session;

import it.jcontacts.hibernate.HibernateUtil;
import it.jcontacts.models.Contatti;
/**
 * Manager che esegue le operazioni sul db relative alla gestione dei contatti
 * Usa il Singleton Pattern 
 * @author Marco
 *
 */
public class ContattiManager {
	
	private static ContattiManager instance;
	
	public static ContattiManager getInstance() {
		if(instance==null) instance=new ContattiManager();
		return instance;
	} 

	/**
	 * Ricerca nel db tutti i contatti filtrandoli per nome,cognome.email,indirizzo e telefono
	 * @param indizio filtro di ricerca
	 * @return lista dei contatti trovati
	 */
	@SuppressWarnings("unchecked")
	public List<Contatti> elenco(String indizio){
		Session session = HibernateUtil.getSessionFactory().openSession();		
		session.beginTransaction();
		
		String query="FROM Contatti c WHERE 1=1";
		if(!indizio.isEmpty()) {			
			//for(String pezzo:indizio.split(" "))
				query+=" AND (c.nome like '%"+indizio+"%' OR c.cognome like '%"+indizio+"%' OR c.email like '%"+indizio+"%' OR c.indirizzo like '%"+indizio+"%' OR c.telefono like '%"+indizio+"%')";
		}
		query+=" ORDER BY NOME,COGNOME";
		List<Contatti> contatti = (List<Contatti>) session.createQuery(query).list();
		
		session.getTransaction().commit();
		session.close();
		return contatti;
	}

	/**
	 * Salva il contatto passato sul db
	 * @param c contatto da salvare
	 * @return nuovo id del contatto salvato
	 * @throws Exception eccezione generata se il contatto è già presente in rubrica
	 */
	@SuppressWarnings("unchecked")
	public Integer salva(Contatti c) throws Exception {		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		String query="FROM Contatti WHERE nome='"+c.getNome()+"' AND cognome='"+c.getCognome()+"' AND idContatto<>"+c.getIdContatto();
		List<Contatti> contatti = (List<Contatti>) session.createQuery(query).list();
		if(contatti.size()>0) {
			throw new Exception("Contatto presente nella rubrica");
		}
		session.saveOrUpdate(c);
		session.getTransaction().commit();
		session.close();
		return c.getIdContatto();

	}

	/**
	 * Elimina il contatto rispondente all'id passato
	 * @param id del contatto da eliminaer
	 */
	public void elimina(Integer id) throws Exception {			
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(session.get(Contatti.class, id));
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * Legge dal db e restituisce il contatto rispondente all'id passato
	 * @param id del contatto da cercare
	 * @return Contatto cercato
	 */
	public Contatti leggi(Integer id) {					
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();			
		Contatti c=session.get(Contatti.class, id);
		session.getTransaction().commit();
		session.close();
		return c;
	}
	
}
