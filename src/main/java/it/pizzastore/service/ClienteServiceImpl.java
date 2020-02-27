package it.pizzastore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.Cliente;
import it.pizzastore.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> listAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> listAllActive() {
		Cliente example = new Cliente();
		example.setAttivo(true);
		return findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente caricaSingolo(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Cliente o) {
		clienteRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Cliente o) {
		clienteRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Cliente o) {
		clienteRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findByExample(Cliente example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		// Match string containing pattern .withIgnoreCase();
		return (List<Cliente>) clienteRepository.findAll(Example.of(example, matcher));
	}

	@Transactional
	@Override
	public void disattiva(Cliente cliente) {
		Cliente clienteDaDisattivare = clienteRepository.findById(cliente.getId()).orElse(null);
		if (clienteDaDisattivare != null) {
			clienteDaDisattivare.setAttivo(false);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Cliente> cercaByCognomeLike(String cognome) {
		Cliente example = new Cliente();
		example.setCognome(cognome);
		example.setAttivo(true);
		return this.findByExample(example);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> cercaByNomeCompletoLike(String term){
		String query = ""
				+ "SELECT " + 
				"    x.id as id, x.nome as nome, x.cognome as cognome, x.via as via, x.civico as civico, x.citta as citta, x.telefono as telefono, x.attivo " + 
				"FROM " + 
				"    (SELECT " + 
				"        c.id, c.nome, c.cognome, c.via, c.civico, c.citta, c.telefono, c.attivo,CONCAT(c.nome, ' ', c.cognome) AS NomeCompleto " + 
				"    FROM " + 
				"        cliente c) AS x " + 
				"WHERE " + 
				"    NomeCompleto LIKE '%"+term+"%' and x.attivo = 1";
		
		@SuppressWarnings("unchecked")
		List<Cliente> result = entityManager.createNativeQuery(query, Cliente.class).getResultList();
		
		return result ;
		//return clienteRepository.findByNomeCompletoLike(term);
	}

}
