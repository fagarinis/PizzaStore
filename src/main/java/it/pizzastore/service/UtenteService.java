package it.pizzastore.service;

import java.util.List;

import it.pizzastore.model.CodiceRuolo;
import it.pizzastore.model.Utente;

public interface UtenteService extends IBaseService<Utente> {

	public Utente eseguiAccesso(String username, String password);

	public Utente caricaSingoloUtenteEager(long id);

	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli);
	
	public Utente cercaDaUsername(String username);

	List<Utente> cercaUtentiByCodiceRuoloAndCognomeLike(CodiceRuolo codiceRuolo, String cognome);

}
