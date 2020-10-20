package br.com.searadivina.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.searadivina.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class FornecedorEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
	private Integer id;
	
	@Column(name = "ds_nome")
	private String nome;
	
	@Column(name = "ds_email")
	private String email;
	
	@Column(name = "nu_cpfOuCnpj")
	private String cpfOuCnpj;
	
	@Column(name = "nu_tipo")
	private Integer tipo;
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE_FORNECEDOR")
	private Set<String> telefones = new HashSet<>();
	
	public FornecedorEntity(Integer id, String nome, String email, String cpfOuCnpj, TipoPessoa tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null)? null: tipo.getCod();
	}
	
}
