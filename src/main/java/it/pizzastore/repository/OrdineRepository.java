package it.pizzastore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, QueryByExampleExecutor<Ordine> {

	@Query("Select o from Ordine o ORDER BY data")
	List<Ordine> findAllOrderByData();

	@Query("Select o from Ordine o join fetch o.pizze join fetch o.utente join fetch o.cliente where o.id =?1")
	Ordine findByIdEager(Long id);

	@Query("select o from Ordine o join fetch o.utente u where o.closed = 0 and u.id =?1 ORDER BY o.data")
	List<Ordine> listAllActiveOrderOfUserOrderByDate(long fattorinoId);

}
