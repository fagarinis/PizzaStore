package it.pizzastore.service;

import java.math.BigDecimal;
import java.util.List;

import it.pizzastore.model.Ordine;

public interface OrdineService extends IBaseService<Ordine> {

	public List<Ordine> findByExampleOrderByData(Ordine example);

	public List<Ordine> listAllOrderByData();

	public Ordine caricaSingoloEager(Long id);

	public BigDecimal calcolaPrezzoOrdine(Ordine o);

	public List<Ordine> listAllOrdiniAttiviUtenteOrdinaPerData(Long fattorinoId);

	public void chiudiOrdine(Long idOrdine);

	public List<Ordine> cercaDaDataEIdPizzaEIdCliente(String simpleData, Long idPizza, Long idCliente);
}
