package it.pizzastore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>, QueryByExampleExecutor<Ingrediente> {

}
