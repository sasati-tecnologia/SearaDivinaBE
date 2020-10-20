package br.com.searadivina.dto;

import java.io.Serializable;

import br.com.searadivina.model.CidadeEntity;

public class CidadeEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	
	public CidadeEntityDTO() {
	}

	public CidadeEntityDTO(CidadeEntity obj) {
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