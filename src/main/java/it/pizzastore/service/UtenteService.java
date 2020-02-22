package it.pizzastore.service;

import java.util.List;

import it.pizzastore.model.Utente;

public interface UtenteService extends IBaseService<Utente> {

	public Utente eseguiAccesso(String username, String password);

	public Utente caricaSingoloUtenteEager(long id);

	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli);

}
