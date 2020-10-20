package br.com.searadivina.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnamineseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String sobreSeara;
	
	private String expectativaSeara;
	
	private String contatoNome;
	
	private String contatoTelefone;
	
	private String contatoNome2;
	
	private String contatoTelefone2;
	
	private Boolean restricaoFisica;
	
	private String rfDetalhes;
	
	private Boolean problemaSaude;
	
	private String psDetalhes;
	
	private Boolean medicamento;
	
	private String medicamentoDetalhes;
	
	private String contatoMedicoNome;
	
	private String contatoMedicoEndereco;
	
	private String contatoMedicoTelefone;
	
	private Boolean restricaoAlimentar;
	
	private String raDetalhes;
	
	private String aspectoEmocional;
	
	private Boolean deprimido;
	
	private Boolean ansioso;
	
	private Boolean tratamentoPsicologico;
	
	private String tpPeriodo;
	
	private String psicologoNome;
	
	private Boolean usoIntorpecentes;
	
	private String uiTipo;
	
	private String uiPeriodo;
	
	private String observacoes;
	
	private Integer associadoId;
}
