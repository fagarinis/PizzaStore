package it.pizzastore.repository;

import java.math.BigDecimal;
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
	
	@Query(nativeQuery = true, value = "SELECT " + 
			"    SUM(prezzoTotalePizza) " + 
			"FROM " + 
			"    (SELECT " + 
			"        p.id, " + 
			"            p.descrizione, " + 
			"            COUNT(p.id) * (SELECT " + 
			"                    piz.prezzoBase + IF(SUM(i.prezzo) IS NULL, 0, SUM(i.prezzo)) " + 
			"                FROM " + 
			"                    pizza piz " + 
			"                LEFT OUTER JOIN pizza_ingrediente pi ON piz.id = pi.pizza_id " + 
			"                LEFT OUTER JOIN ingrediente i ON pi.ingrediente_id = i.id " + 
			"                WHERE " + 
			"                    piz.id = p.id) AS prezzoTotalePizza " + 
			"    FROM " + 
			"        ordine o " + 
			"    JOIN ordine_pizza op ON o.id = op.ordine_id " + 
			"    JOIN pizza p ON op.pizza_id = p.id " + 
			"    WHERE " + 
			"        o.id = ?1 " + 
			"    GROUP BY p.id) AS x;")
	BigDecimal getPrezzoTotaleOrdine(Long id);

}
