package it.pizzastore.service;

import java.util.List;

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

	@Autowired
	ClienteRepository clienteRepository;

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

}
