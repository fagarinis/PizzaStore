package it.pizzastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, QueryByExampleExecutor<Cliente> {

//	@Query(nativeQuery = true, value = ""
//			+ "SELECT " + 
//			"    x.id, x.nome, x.cognome, x.via, x.civico, x.citta, x.telefono, x.attivo " + 
//			"FROM " + 
//			"    (SELECT " + 
//			"        c.id, c.nome, c.cognome, c.via, c.civico, c.citta, c.telefono, c.attivo,CONCAT(c.nome, ' ', c.cognome) AS NomeCompleto " + 
//			"    FROM " + 
//			"        cliente c) AS x " + 
//			"WHERE " + 
//			"    NomeCompleto LIKE '%?1%' and x.attivo = 1")
//	List<Cliente> findByNomeCompletoLike(String nomeCompleto);
	
}
