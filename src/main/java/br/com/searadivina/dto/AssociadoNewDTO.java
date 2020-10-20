package br.com.searadivina.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.searadivina.services.validation.AssociadoInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AssociadoInsert
public class AssociadoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String sexo;
	
	//@NotEmpty(message = "Preenchimento obrigatório")
	private LocalDate dataNascimento;
	
	private Integer idade;
	
	private String estadoCivil;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	private String profissao;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String escolaridade;
	
	private String status;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
 	private Integer cidadeId;
 	
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
}
