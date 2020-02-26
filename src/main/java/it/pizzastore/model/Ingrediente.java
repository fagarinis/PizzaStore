package it.pizzastore.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	private BigDecimal prezzo;
	private String codice;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredienti")
	private Set<Pizza> pizze = new HashSet<>();

	public Ingrediente() {
	}

	public Ingrediente(Long id) {
		this.id = id;
	}

	public Ingrediente(String descrizione, String codice, String prezzo) {
		this.descrizione = descrizione;
		this.codice = codice;
		this.setPrezzo(prezzo);
	}

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

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
