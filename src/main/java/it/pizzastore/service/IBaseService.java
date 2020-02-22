package it.pizzastore.service;

import java.util.List;

public interface IBaseService<T> {

	public List<T> listAll();

	public T caricaSingolo(Long id);

	public void aggiorna(T o);

	public void inserisciNuovo(T o);

	public void rimuovi(T o);

	public List<T> findByExample(T example);

}
