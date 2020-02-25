package it.pizzastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, QueryByExampleExecutor<Pizza> {

	@Query("select p from Pizza p left join fetch p.ingredienti i where p.id = ?1")
	Pizza findByIdWithIngredients(Long id);

	@Query("select distinct p from Pizza p left join fetch p.ingredienti i where p.attivo = 1")
	List<Pizza> findAllActiveEager();

	List<Pizza> findByDescrizioneContaining(String term);
}
