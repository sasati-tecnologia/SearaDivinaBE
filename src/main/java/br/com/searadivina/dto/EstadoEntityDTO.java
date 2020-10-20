package br.com.searadivina.dto;

import java.io.Serializable;

import br.com.searadivina.model.EstadoEntity;

public class EstadoEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	
	public EstadoEntityDTO() {
	}

	public EstadoEntityDTO(EstadoEntity obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}