package it.pizzastore.service;

import java.util.List;

import it.pizzastore.model.Ordine;

public interface OrdineService extends IBaseService<Ordine> {

	public List<Ordine> findByExampleOrderByData(Ordine example);

	public List<Ordine> listAllOrderByData();

	public Ordine caricaSingoloEager(Long id);

}
