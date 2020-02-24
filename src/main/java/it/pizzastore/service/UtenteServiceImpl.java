package it.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.CodiceRuolo;
import it.pizzastore.model.Ruolo;
import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;
import it.pizzastore.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;

	@Autowired
	private RuoloService ruoloService;

	@Transactional(readOnly = true)
	public List<Utente> listAll() {
		return (List<Utente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingolo(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		repository.delete(utenteInstance);

	}

	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente utenteExample) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);// Match string
																										// containing
																										// pattern
		// .withIgnoreCase();
		return (List<Utente>) repository.findAll(Example.of(utenteExample, matcher));

	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return repository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente caricaSingoloUtenteEager(long id) {
		return repository.findByIdEager(id);
	}

	@Transactional
	@Override
	public void aggiornaUtenteConRuoli(Utente utenteModel, List<String> listaIdRuoli) {
		utenteModel.getRuoli().clear();
		for (String idRuolo : listaIdRuoli) {
			Ruolo ruoloDaAggiungere = ruoloService.caricaSingolo(Long.parseLong(idRuolo));
			if (ruoloDaAggiungere != null)
				utenteModel.getRuoli().add(ruoloDaAggiungere);
		}
		aggiorna(utenteModel);
	}

	@Transactional(readOnly = true)
	@Override
	public Utente cercaDaUsername(String username) {
		return repository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Utente> cercaUtentiByCodiceRuoloAndCognomeLike(CodiceRuolo codiceRuolo, String cognome) {
		return repository.findUsersByRoleCodeAndSurnameLike(codiceRuolo, cognome);
	}

}
