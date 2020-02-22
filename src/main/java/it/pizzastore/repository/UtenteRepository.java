package it.pizzastore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {
	
	@Query("select u from Utente u left join fetch u.ruoli r where u.username = ?1 and u.password = ?2 and u.stato = ?3")
	Utente findByUsernameAndPasswordAndStato(String username, String password, StatoUtente stato);
	
	@Query("select u from Utente u left join fetch u.ruoli r where u.id = ?1")
	Utente findByIdEager(Long id);
}
