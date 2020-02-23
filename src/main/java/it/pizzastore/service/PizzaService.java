package it.pizzastore.service;

import java.math.BigDecimal;

import it.pizzastore.model.Pizza;

public interface PizzaService extends IBaseService<Pizza> {
	
	public Pizza caricaSingolaPizzaConIngredienti(Long id);

	public void aggiornaConIngredienti(Pizza pizzaInstance);
	
	public BigDecimal calcolaPrezzoPizza(Pizza pizzaInstance);

}
