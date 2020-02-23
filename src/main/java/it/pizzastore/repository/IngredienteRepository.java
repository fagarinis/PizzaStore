package it.pizzastore.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>, QueryByExampleExecutor<Ingrediente> {

	@Query("select distinct i from Ingrediente i join fetch i.pizze p where p.id = ?1")
	Set<Ingrediente> findByIdPizza(Long idPizza);
}
