package it.pizzastore.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.pizzastore.model.Ingrediente;
import it.pizzastore.model.Pizza;

public class IngredienteDTO {

	private Long id;
	private String descrizione;
	private BigDecimal prezzo;
	private String codice;

	private Set<Pizza> pizze = new HashSet<>();

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

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public void setPrezzo(String prezzoInput) {
		try {
			this.setPrezzo(new BigDecimal(prezzoInput));
		} catch (Exception e) {
		}
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.codice)) {
			result.add("il campo Codice non può essere vuoto");
		}
		if (StringUtils.isBlank(this.descrizione)) {
			result.add("il campo Descrizione non può essere vuoto");
		}
		if (this.prezzo == null || this.prezzo.compareTo(BigDecimal.ZERO) == -1) {
			result.add("il campo Prezzo non è valido");
		}

		return result;
	}

	public static Ingrediente buildModelFromDto(IngredienteDTO ingredienteDTO) {
		Ingrediente result = new Ingrediente();

		result.setId(ingredienteDTO.getId());
		result.setCodice(ingredienteDTO.getCodice());
		result.setDescrizione(ingredienteDTO.getDescrizione());
		result.setPrezzo(ingredienteDTO.getPrezzo());
		result.setPizze(ingredienteDTO.getPizze());

		return result;
	}

}
