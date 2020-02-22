package it.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.Ordine;
import it.pizzastore.repository.OrdineRepository;

@Service
public class OrdineServiceImpl implements OrdineService {
	
	@Autowired
	OrdineRepository ordineRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Ordine> listAll() {
		return (List<Ordine>) ordineRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Ordine caricaSingolo(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Ordine o) {
		ordineRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Ordine o) {
		ordineRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Ordine o) {
		ordineRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ordine> findByExample(Ordine example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		// Match string containing pattern .withIgnoreCase();
		return (List<Ordine>) ordineRepository.findAll(Example.of(example, matcher));
	}

}
