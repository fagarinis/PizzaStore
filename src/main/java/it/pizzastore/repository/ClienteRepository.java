package it.pizzastore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pizzastore.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, QueryByExampleExecutor<Cliente> {

}
