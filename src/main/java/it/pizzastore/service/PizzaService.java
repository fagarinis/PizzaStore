package it.pizzastore.service;

import java.math.BigDecimal;
import java.util.List;

import it.pizzastore.model.Pizza;

public interface PizzaService extends IBaseService<Pizza> {
	
	public Pizza caricaSingolaPizzaConIngredienti(Long id);

	public void aggiornaConIngredienti(Pizza pizzaInstance);
	
	public BigDecimal calcolaPrezzoPizza(Pizza pizzaInstance);

	public List<Pizza> listAllActiveEager();
	
	public void disattiva(Pizza pizza);

	public List<Pizza> cercaByDescrizioneLike(String term);

}
