package it.pizzastore.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.repository.IngredienteRepository;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
	IngredienteRepository ingredienteRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Ingrediente> listAll() {
		return (List<Ingrediente>) ingredienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Ingrediente caricaSingolo(Long id) {
		return ingredienteRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Ingrediente o) {
		ingredienteRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Ingrediente o) {
		ingredienteRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Ingrediente o) {
		ingredienteRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ingrediente> findByExample(Ingrediente example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		// Match string containing pattern .withIgnoreCase();
		return (List<Ingrediente>) ingredienteRepository.findAll(Example.of(example, matcher));
	}

	@Transactional(readOnly = true)
	@Override
	public Set<Ingrediente> cercaIngredientiDaIdPizza(Long idPizza) {
		return ingredienteRepository.findByIdPizza(idPizza);
	}

}
