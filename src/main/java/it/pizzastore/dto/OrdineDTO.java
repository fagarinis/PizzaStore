package it.pizzastore.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pizzastore.model.Cliente;
import it.pizzastore.model.Ordine;
import it.pizzastore.model.Pizza;
import it.pizzastore.model.Utente;

public class OrdineDTO {

	private Long id;
	private Date data;
	private Boolean closed = false;
	private String codice;
	private BigDecimal costoTotale;

	private List<Pizza> pizze = new ArrayList<>();

	// fattorino
	private Utente utente;

	private Cliente cliente;

	public OrdineDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean isClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public BigDecimal getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(BigDecimal costoTotale) {
		this.costoTotale = costoTotale;
	}

	public void setCostoTotale(String costoTotale) {
		try {
			this.setCostoTotale(new BigDecimal(costoTotale));
		} catch (Exception e) {
		}
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setPizze(String[] idPizzeInput, String[] numeroPizzeInput) {
		if (idPizzeInput == null || numeroPizzeInput == null) {
			return;
		}
		// converto gli array di stringhe (id pizze checkate)
		List<String> idPizzeInputList = Arrays.asList(idPizzeInput);

		// converto gli input (numero pizze) rimuovendo gli input vuoti
		List<String> numeroPizzeInputList = new ArrayList<>();
		numeroPizzeInputList.addAll(Arrays.asList(numeroPizzeInput));
		numeroPizzeInputList.removeIf(n -> StringUtils.isBlank(n));

		// ogni id pizza deve corrispondere a un numero di pizze con quell'id
		if (idPizzeInputList.size() != numeroPizzeInputList.size()) {
			return;
		}

		// creo la lista di oggetti Pizza con solo l'id
		List<Pizza> pizze = new ArrayList<>();
		for (int i = 0; i < idPizzeInputList.size(); i++) {
			for (int j = 0; j < Integer.parseInt(numeroPizzeInputList.get(i)); j++) {
				Pizza pizza = new Pizza(Long.parseLong(idPizzeInputList.get(i)));
				pizze.add(pizza);
			}
		}

		// setto la lista di pizze nel DTO
		this.setPizze(pizze);

	}

	public void setData(String dataInput) {

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dataInput);
		} catch (ParseException e) {
		}
		this.setData(date);
	}

	public void setCliente(String clienteId) {
		if (clienteId == null) {
			return;
		}
		try {
			Long id = Long.parseLong(clienteId);
			this.setCliente(new Cliente(id));
		} catch (Exception e) {
		}

	}

	public void setUtente(String fattorinoId) {
		if (fattorinoId == null) {
			return;
		}
		try {
			Long id = Long.parseLong(fattorinoId);
			this.setUtente(new Utente(id));
		} catch (Exception e) {
		}

	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.codice)) {
			result.add("il campo Codice non può essere vuoto");
		}
		if (this.getPizze().size() == 0) {
			result.add("Scegliere almeno una pizza");
		}
		if (this.getCliente() == null || this.getCliente().getId() == null) {
			result.add("il campo Cliente non può essere vuoto");
		}

		if (this.getUtente() == null || this.getUtente().getId() == null) {
			result.add("il campo Fattorino non può essere vuoto");
		}

		return result;
	}

	public static Ordine buildModelFromDto(OrdineDTO ordineDTO) {
		Ordine result = new Ordine();

		result.setId(ordineDTO.getId());
		result.setCodice(ordineDTO.getCodice());
		result.setData(ordineDTO.getData());
		result.setPizze(ordineDTO.getPizze());
		result.setUtente(ordineDTO.getUtente());
		result.setCliente(ordineDTO.getCliente());
		result.setClosed(ordineDTO.isClosed());
		
		result.setCostoTotale(ordineDTO.getCostoTotale());

		return result;
	}

	public void setClosed(String chiusoInput) {
		Boolean booleanNullo = null;
		if (chiusoInput == null) {

			this.setClosed(booleanNullo);
		}

		if (chiusoInput.equalsIgnoreCase("true") || chiusoInput.equalsIgnoreCase("false")) {
			this.setClosed(Boolean.parseBoolean(chiusoInput));
		} else {
			this.setClosed(booleanNullo);
		}
	}
	
	public Long getNumeroPizzeConId(Long id) {
		Long numeroPizze = this.getPizze().stream().filter(pizza->pizza.getId() == id).count();
		if(numeroPizze == 0) {
			return null;
		}
		return numeroPizze;
	}

}
