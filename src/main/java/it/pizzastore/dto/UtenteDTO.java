package it.pizzastore.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import it.pizzastore.model.Ruolo;
import it.pizzastore.model.StatoUtente;
import it.pizzastore.model.Utente;


public class UtenteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private Date dataRegistrazione;
	private StatoUtente stato = StatoUtente.CREATO;
	private Set<Ruolo> ruoli = new HashSet<>();

	private List<String> idRuoli = new ArrayList<>();

	public UtenteDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public List<String> getIdRuoli() {
		return this.idRuoli;
	}

	public void setIdRuoli(String[] idRuoli) {

		if (idRuoli == null) {
			return;
		}

		for (String idRuolo : idRuoli) {
			this.idRuoli.add(idRuolo);
		}
	}
	
	public void setRuoli(String[] idRuoli) {
		if (idRuoli == null) {
			return;
		}
		for (String idRuolo : idRuoli) {
			this.ruoli.add(new Ruolo(Long.parseLong(idRuolo)));
		}
		
	}

	public void populateRuoli() {
		for (String idRuolo : idRuoli) {
			Long idRuoloDaAggiungere = Long.parseLong(idRuolo);
			Ruolo ruoloDaAggiungere = new Ruolo(idRuoloDaAggiungere);
			this.ruoli.add(ruoloDaAggiungere);
		}
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("il campo Nome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("il campo Cognome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.username)) {
			result.add("il campo Username non può essere vuoto");
		}
		if (StringUtils.isBlank(this.password)) {
			result.add("il campo Password non può essere vuoto");
		}

		return result;
	}

	public static Utente buildModelFromDto(UtenteDTO utenteDTO) {
		Utente result = new Utente();
		result.setId(utenteDTO.getId());
		result.setNome(utenteDTO.getNome());
		result.setCognome(utenteDTO.getCognome());
		result.setUsername(utenteDTO.getUsername());
		result.setPassword(utenteDTO.getPassword());
		result.setDataRegistrazione(utenteDTO.getDataRegistrazione());
		result.setStato(utenteDTO.getStato());

		return result;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

}
