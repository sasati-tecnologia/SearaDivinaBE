package br.com.searadivina.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class ProdutoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Integer id;
	
	@Column(name = "id_categoria")
	private Integer categoria;
	
	@Column(name = "id_associado")
	private Integer associado;

	@Column(name = "id_fornecedor")
	private Integer fornecedor;

	@Column(name = "ds_produto")
	private String produto;

	@Column(name = "ds_pagamento")
	private String pagamento;

	@Column(name = "dt_vencimento")
	private LocalDate vencimento;

	@Column(name = "dt_lancamento")
	private LocalDate lancamento;

	@Column(name = "vl_pagamento")
	private Double valor;

	@Column(name = "vl_taxa")
	private Double taxa;

	@Column(name = "vl_juros")
	private Double juros;
	
	
}
