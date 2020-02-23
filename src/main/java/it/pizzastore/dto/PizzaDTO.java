package it.pizzastore.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Ordine;
import it.pizzastore.model.Pizza;

public class PizzaDTO {

	private Long id;
	private String descrizione;
	private String codice;
	private BigDecimal prezzoBase;
	private Boolean attivo;

	private Set<Ingrediente> ingredienti = new HashSet<>();
	private Set<Ordine> ordini = new HashSet<>();

	private List<String> idIngredienti = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public BigDecimal getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(BigDecimal prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public void setPrezzoBase(String prezzoBase) {
		try {
			this.setPrezzoBase(new BigDecimal(prezzoBase));
		} catch (Exception e) {
		}
	}

	public Boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public Set<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(Set<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Set<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(Set<Ordine> ordini) {
		this.ordini = ordini;
	}

	public void setIngredienti(String[] idIngredienti) {
		if (idIngredienti == null) {
			this.getIdIngredienti().clear();
			return;
		}
		this.setIdIngredienti(Arrays.asList(idIngredienti));

		for (String idIngrediente : idIngredienti) {
			this.ingredienti.add(new Ingrediente(Long.parseLong(idIngrediente)));
		}

	}

	public void setOrdini(String[] idOrdini) {
		if (idOrdini == null) {
			return;
		}
		for (String idOrdine : idOrdini) {
			this.ordini.add(new Ordine(Long.parseLong(idOrdine)));
		}

	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.codice)) {
			result.add("il campo Codice non può essere vuoto");
		}
		if (StringUtils.isBlank(this.descrizione)) {
			result.add("il campo Descrizione non può essere vuoto");
		}
		if (this.prezzoBase == null || this.prezzoBase.compareTo(BigDecimal.ZERO) == -1) {
			result.add("il campo Prezzo non è valido");
		}

		return result;
	}

	public static Pizza buildModelFromDto(PizzaDTO pizzaDTO) {
		Pizza result = new Pizza();

		result.setId(pizzaDTO.getId());
		result.setCodice(pizzaDTO.getCodice());
		result.setDescrizione(pizzaDTO.getDescrizione());
		result.setPrezzoBase(pizzaDTO.getPrezzoBase());
		result.setIngredienti(pizzaDTO.getIngredienti());
		result.setOrdini(pizzaDTO.getOrdini());

		result.setAttivo(pizzaDTO.isAttivo());

		return result;
	}

	public List<String> getIdIngredienti() {
		return idIngredienti;
	}

	public void setIdIngredienti(List<String> idIngredienti) {
		this.idIngredienti = idIngredienti;
	}

	public void setAttivo(String attivo) {
		if (attivo.equalsIgnoreCase("true") || attivo.equalsIgnoreCase("false")) {
			this.attivo = Boolean.valueOf(attivo);
		}
	}

	@Override
	public String toString() {
		return "PizzaDTO [id=" + id + ", descrizione=" + descrizione + ", codice=" + codice + ", prezzoBase="
				+ prezzoBase + ", attivo=" + attivo + ", ingredienti=" + ingredienti + ", ordini=" + ordini
				+ ", idIngredienti=" + idIngredienti + "]";
	}

}
