package it.pizzastore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Ordine;
import it.pizzastore.model.Pizza;
import it.pizzastore.repository.IngredienteRepository;
import it.pizzastore.repository.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Autowired
	PizzaRepository pizzaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Pizza> listAll() {
		return (List<Pizza>) pizzaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Pizza> listAllActive() {
		Pizza example = new Pizza();
		example.setAttivo(true);
		return this.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public Pizza caricaSingolo(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void aggiorna(Pizza o) {
		pizzaRepository.save(o);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Pizza o) {
		pizzaRepository.save(o);
	}

	@Transactional
	@Override
	public void rimuovi(Pizza o) {
		pizzaRepository.delete(o);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pizza> findByExample(Pizza example) {
		ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING);
		// Match string containing pattern .withIgnoreCase();
		return (List<Pizza>) pizzaRepository.findAll(Example.of(example, matcher));
	}

	@Transactional(readOnly = true)
	@Override
	public Pizza caricaSingolaPizzaConIngredienti(Long id) {
		return pizzaRepository.findByIdWithIngredients(id);
	}

	@Transactional
	@Override
	public void aggiornaConIngredienti(Pizza pizzaInstance) {
		
		List<Long> idIngredienti = new ArrayList<>();
		
		for (Ingrediente ingrediente : pizzaInstance.getIngredienti()) {
			idIngredienti.add(ingrediente.getId());
		}

		List<Ingrediente> ingredientiPersistList = (List<Ingrediente>) ingredienteRepository.findAllById(idIngredienti);
		Set<Ingrediente> ingredientiPersist = ingredientiPersistList.stream().collect(Collectors.toSet());
		
		pizzaInstance.setIngredienti(ingredientiPersist);
		this.aggiorna(pizzaInstance);

	}

	@Transactional(readOnly = true)
	@Override
	public BigDecimal calcolaPrezzoPizza(Pizza pizzaInstance) {
		Pizza pizzaPersist = pizzaRepository.findByIdWithIngredients(pizzaInstance.getId());
		BigDecimal prezzoPizza = pizzaPersist.getPrezzoBase();
		for (Ingrediente ingrediente : pizzaPersist.getIngredienti()) {
			prezzoPizza = prezzoPizza.add(ingrediente.getPrezzo());
		}

		return prezzoPizza;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Pizza> listAllActiveEager() {
		return pizzaRepository.findAllActiveEager();
	}
	
	@Transactional
	@Override
	public void disattiva(Pizza pizza) {
		Pizza pizzaDaDisattivare = pizzaRepository.findById(pizza.getId()).orElse(null);
		if (pizzaDaDisattivare != null) {
			pizzaDaDisattivare.setAttivo(false);
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Pizza> cercaByDescrizioneLike(String term) {
		return pizzaRepository.findByDescrizioneContaining(term);
	}

	/**
	 * Riceva una pizza che contiene ingredienti transient con solo id
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Pizza> findByExampleConIdIngredienti(Pizza example) {
		String query = "select distinct p from Pizza p left join p.ingredienti i "
				+ " where p.id = p.id "
				+ " and p.attivo = 1 ";

		if (StringUtils.isNotEmpty(example.getCodice()))
			query += " and p.codice like '%" + example.getCodice() + "%' ";
		if (StringUtils.isNotEmpty(example.getDescrizione()))
			query += " and p.descrizione like '%" + example.getDescrizione() + "%' ";
		if (example.getPrezzoBase() != null)
			query += " and p.prezzoBase = "+ example.getPrezzoBase() +" ";
		for(Ingrediente ingrediente: example.getIngredienti()) {
			query += " and i.id = "+ ingrediente.getId(); 
		}

		return entityManager.createQuery(query, Pizza.class).getResultList();
	}

}
