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
import it.pizzastore.model.utils.IntegerUtils;
import it.pizzastore.repository.OrdineRepository;

@Service
public class OrdineServiceImpl implements OrdineService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private OrdineRepository ordineRepository;

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
		// setto il prezzo dopo aver aggiornato le pizze
		ordinePersist.setCostoTotale(calcolaPrezzoOrdine(ordinePersist));

		// il save non funzionava per via del fatto che un
		// ordine può avere più pizze dello stesso tipo
	}

	@Transactional
	@Override
	public void inserisciNuovo(Ordine o) {
		o.setClosed(false);
		o.setData(new Date());
		ordineRepository.save(o);
		// setto il prezzo dopo aver settato le pizze
		o.setCostoTotale(calcolaPrezzoOrdine(o));
	}

	/**
	 * evocato su un ordine a cui sono già state collegate le pizze sul DB
	 */
	@Transactional(readOnly = true)
	@Override
	public BigDecimal calcolaPrezzoOrdine(Ordine o) {
		return ordineRepository.getPrezzoTotaleOrdine(o.getId());
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

	@Transactional(readOnly = true)
	@Override
	public List<Ordine> findByExampleOrderByData(Ordine example) {
		String query = "select o from Ordine o where o.id = o.id ";

		if (StringUtils.isNotEmpty(example.getCodice()))
			query += " and o.codice like '%" + example.getCodice() + "%' ";
		if (example.getCostoTotale() != null) {
			if (IntegerUtils.isIntegerValue(example.getCostoTotale())) {
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

	@Override
	public List<Ordine> cercaDaDataEIdPizzaEIdCliente(String simpleData, Long idPizza, Long idCliente) {
		String query = "select distinct o from Ordine o left join o.pizze p left join o.cliente c where 1 = 1 ";

		if (simpleData != null)
			query += " and o.data like '%" + simpleData + "%' ";
		if (idPizza != null)
			query += " and p.id =" + idPizza + " ";
		if (idCliente != null)
			query += " and c.id =" + idCliente + " ";

		query += "ORDER BY o.data";

		return entityManager.createQuery(query, Ordine.class).getResultList();
	}

}
