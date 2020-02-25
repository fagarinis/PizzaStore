package it.pizzastore.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
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

	// questo mi serve per il findByExample2 che risulta 'a mano'
	// o comunque in tutti quei casi in cui ho bisogno di costruire custom query nel
	// service
	@PersistenceContext
	private EntityManager entityManager;

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
	public List<Ordine> listAllOrderByData() {
		return (List<Ordine>) ordineRepository.findAllOrderByData();
	}

	@Transactional(readOnly = true)
	@Override
	public Ordine caricaSingolo(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Ordine caricaSingoloEager(Long id) {
		return ordineRepository.findByIdEager(id);
	}

	@Transactional
	@Override
	public void aggiorna(Ordine o) {
		Ordine ordinePersist = this.caricaSingolo(o.getId());
		ordinePersist.setCodice(o.getCodice());
		ordinePersist.setPizze(o.getPizze());
		ordinePersist.setUtente(o.getUtente());
		ordinePersist.setCliente(o.getCliente());

		// il save non funzionava per via del fatto che un
		// ordine può avere più pizze dello stesso tipo
	}

	@Transactional
	@Override
	public void inserisciNuovo(Ordine o) {
		o.setCostoTotale(calcolaPrezzoOrdine(o));
		o.setClosed(false);
		o.setData(new Date());

		ordineRepository.save(o);
	}

	/**
	 * viene evocato su un ordine transient che contiene pizze transient con solo id
	 */
	@Transactional(readOnly = true)
	@Override
	public BigDecimal calcolaPrezzoOrdine(Ordine o) {

		BigDecimal costoOrdineTotale = BigDecimal.ZERO;
		for (Pizza pizza : o.getPizze()) {
			Pizza pizzaInOrdine = pizzaService.caricaSingolaPizzaConIngredienti(pizza.getId());
			BigDecimal costoPizza = pizzaInOrdine.getPrezzo();
			costoOrdineTotale = costoOrdineTotale.add(costoPizza);
		}

		return costoOrdineTotale;
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

	private boolean isIntegerValue(BigDecimal bd) {
		return bd.stripTrailingZeros().scale() <= 0;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ordine> findByExampleOrderByData(Ordine example) {
		String query = "select o from Ordine o where o.id = o.id ";

		if (StringUtils.isNotEmpty(example.getCodice()))
			query += " and o.codice like '%" + example.getCodice() + "%' ";
		if (example.getCostoTotale() != null) {
			if (isIntegerValue(example.getCostoTotale())) {
				query += " and floor(o.costoTotale) =" + example.getCostoTotale() + " ";
			} else {
				query += " and o.costoTotale =" + example.getCostoTotale() + " ";
			}
		}
		if (example.getSimpleData() != null)
			query += " and o.data like '%" + example.getSimpleData() + "%' ";
		if (example.isClosed() != null) {
			int b = example.isClosed() ? 1 : 0;
			query += " and o.closed = " + b + " ";
		}

		query += "ORDER BY o.data";

		return entityManager.createQuery(query, Ordine.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ordine> listAllOrdiniAttiviUtenteOrdinaPerData(Long fattorinoId) {
		return ordineRepository.listAllActiveOrderOfUserOrderByDate(fattorinoId);
	}

	@Transactional
	@Override
	public void chiudiOrdine(Long idOrdine) {
		Ordine ordine = this.caricaSingolo(idOrdine);
		if (!ordine.isClosed()) {
			ordine.setClosed(true);
		}
	}

}
