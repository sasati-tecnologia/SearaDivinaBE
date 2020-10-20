package br.com.searadivina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class AnamineseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_anaminese")
	private Integer id;
	
	@Column(name = "ds_sobreSeara")
	private String sobreSeara;
	
	@Column(name = "ds_expectativaSeara")
	private String expectativaSeara;
	
	@Column(name = "ds_contatoNome")
	private String contatoNome;
	
	@Column(name = "nu_contatoTelefone")
	private String contatoTelefone;
	
	@Column(name = "ds_contatoNome2")
	private String contatoNome2;
	
	@Column(name = "nu_contatoTelefone2")
	private String contatoTelefone2;
	
	@Column(name = "ds_restricaoFisica")
	private Boolean restricaoFisica;
	
	@Column(unique=true, name = "ds_rfDetalhes")
	private String rfDetalhes;
	
	@Column(name = "ds_problemaSaude")
	private Boolean problemaSaude;
	
	@Column(unique=true, name = "ds_psDetalhes")
	private String psDetalhes;
	
	@Column(name = "ds_medicamento")
	private Boolean medicamento;
	
	@Column(unique=true, name = "ds_medicamentoDetalhes")
	private String medicamentoDetalhes;
	
	@Column(name = "ds_contatoMedicoNome")
	private String contatoMedicoNome;
	
	@Column(name = "nu_contatoMedicoEndereco")
	private String contatoMedicoEndereco;
	
	@Column(name = "nu_contatoMedicoTelefone")
	private String contatoMedicoTelefone;
	
	@Column(name = "ds_restricaoAlimentar")
	private Boolean restricaoAlimentar;
	
	@Column(unique=true, name = "ds_raDetalhes")
	private String raDetalhes;
	
	@Column(name = "nu_aspectoEmocional")
	private String aspectoEmocional;
	
	@Column(name = "ds_deprimido")
	private Boolean deprimido;
	
	@Column(name = "ds_ansioso")
	private Boolean ansioso;
		
	@Column(name = "ds_tratamentoPsicologico")
	private Boolean tratamentoPsicologico;
	
	@Column(name = "nu_tpPeriodo")
	private String tpPeriodo;
	
	@Column(name = "ds_psicologoNome")
	private String psicologoNome;
	
	@Column(name = "ds_usoIntorpecentes")
	private Boolean usoIntorpecentes;
	
	@Column(name = "ds_uiTipo")
	private String uiTipo;
	
	@Column(name = "ds_uiPeriodo")
	private String uiPeriodo;
	
	@Column(name = "ds_observacoes")
	private String observacoes;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "associado_id")
	private AssociadoEntity associado;
	
}
