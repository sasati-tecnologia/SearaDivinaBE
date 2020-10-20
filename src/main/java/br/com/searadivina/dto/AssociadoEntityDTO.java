package br.com.searadivina.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.services.validation.AssociadoUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AssociadoUpdate
public class AssociadoEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String sexo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private LocalDate dataNascimento;
	
	private Integer idade;
	
	private String estadoCivil;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String rg;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	private String profissao;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String escolaridade;
	
	private String status;

	public AssociadoEntityDTO(AssociadoEntity obj) {
		id = obj.getId();
		nome = obj.getNome();
		sexo = obj.getSexo();
		dataNascimento = obj.getDataNascimento();
		idade = obj.getIdade();
		estadoCivil = obj.getEstadoCivil();
		cpfOuCnpj = obj.getCpfOuCnpj();
		rg = obj.getRg();
		email = obj.getEmail();
		profissao = obj.getProfissao();
		escolaridade = obj.getEscolaridade();
		status = obj.getStatus();
	}
	
	
}
