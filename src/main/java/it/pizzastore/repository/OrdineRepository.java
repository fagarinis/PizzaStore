package it.pizzastore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, QueryByExampleExecutor<Ordine> {

}
