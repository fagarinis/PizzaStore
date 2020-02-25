package it.pizzastore.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pizzastore.model.Cliente;

public class ClienteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String via;
	private String citta;
	private String civico;
	private String telefono;
	private boolean attivo;

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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome)) {
			result.add("il campo Nome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.cognome)) {
			result.add("il campo Cognome non può essere vuoto");
		}
		if (StringUtils.isBlank(this.via)) {
			result.add("il campo Via non può essere vuoto");
		}
		if (StringUtils.isBlank(this.civico)) {
			result.add("il campo Civico non può essere vuoto");
		}
		if (StringUtils.isBlank(this.citta)) {
			result.add("il campo Città non può essere vuoto");
		}
		if (StringUtils.isBlank(this.telefono)) {
			result.add("il campo Telefono non può essere vuoto");
		}

		return result;
	}

	public static Cliente buildModelFromDto(ClienteDTO clienteDTO) {
		Cliente result = new Cliente();

		result.setId(clienteDTO.getId());
		result.setNome(clienteDTO.getNome());
		result.setCognome(clienteDTO.getCognome());
		result.setVia(clienteDTO.getVia());
		result.setCivico(clienteDTO.getCivico());
		result.setCitta(clienteDTO.getCitta());
		result.setTelefono(clienteDTO.getTelefono());
		result.setAttivo(clienteDTO.isAttivo());

		return result;
	}

}
