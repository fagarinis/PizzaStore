package it.pizzastore.service;

import it.pizzastore.model.Pizza;

public interface PizzaService extends IBaseService<Pizza> {
	
	public Pizza caricaSingolaPizzaConIngredienti(Long id);

}
