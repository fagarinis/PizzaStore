package it.pizzastore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>, QueryByExampleExecutor<Ruolo>{

}
