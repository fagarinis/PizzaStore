package it.pizzastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.CodiceRuolo;
import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {
	
	@Query("select u from Utente u left join fetch u.ruoli r where u.username = ?1 and u.password = ?2 and u.stato = ?3")
	Utente findByUsernameAndPasswordAndStato(String username, String password, StatoUtente stato);
	
	@Query("select u from Utente u left join fetch u.ruoli r where u.id = ?1")
	Utente findByIdEager(Long id);
	
	Utente findByUsername(String username);

	@Query("select distinct u from Utente u join fetch u.ruoli r where u.stato ='ATTIVO' and u.cognome like %?2% and r.codice = ?1")
	List<Utente> findUsersByRoleCodeAndSurnameLike(CodiceRuolo codiceRuolo, String cognome);
}
