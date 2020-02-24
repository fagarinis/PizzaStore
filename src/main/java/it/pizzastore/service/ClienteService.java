package it.pizzastore.service;

import java.util.List;

import it.pizzastore.model.Cliente;

public interface ClienteService extends IBaseService<Cliente> {
	
	public List<Cliente> listAllActive();

	public void disattiva(Cliente cliente);

	public List<Cliente> cercaByCognomeLike(String term);

}
