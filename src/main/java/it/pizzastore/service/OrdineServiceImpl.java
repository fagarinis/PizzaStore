package it.pizzastore.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.Ordine;
import it.pizzastore.model.Pizza;
import it.pizzastore.repository.OrdineRepository;

@Service
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository ordineRepository;

	@Autowired
	private PizzaService pizzaService;

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
		BigDecimal costoOrdineTotale = BigDecimal.ZERO;
		for (Pizza pizza : o.getPizze()) {
			Pizza pizzaInOrdine = pizzaService.caricaSingolaPizzaConIngredienti(pizza.getId());
			BigDecimal costoPizza = pizzaInOrdine.getPrezzo();
			costoOrdineTotale = costoOrdineTotale.add(costoPizza);
		}

		o.setCostoTotale(costoOrdineTotale);
		o.setClosed(false);
		o.setData(new Date());
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
