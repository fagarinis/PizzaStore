package it.pizzastore.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	private String codice;
	private BigInteger prezzoBase;
	private boolean attivo = true;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "pizza_ingrediente", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id", referencedColumnName = "ID"))
	private Set<Ingrediente> ingredienti = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pizze")
	private Set<Ordine> ordini = new HashSet<>();

	public Pizza() {
	}

	public Pizza(Long id) {
		this.id = id;
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

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public BigInteger getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(BigInteger prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
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

	public BigInteger calcolaPrezzo() {
		BigInteger result = this.prezzoBase;
		for (Ingrediente ingrediente : ingredienti) {
			result = result.add(ingrediente.getPrezzo());
		}
		return result;
	}

}
