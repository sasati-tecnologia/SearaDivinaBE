package br.com.searadivina.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.searadivina.model.FornecedorEntity;
import br.com.searadivina.services.validation.FornecedorUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FornecedorUpdate
public class FornecedorEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@Column(name = "nu_cpfOuCnpj")
	private String cpfOuCnpj;

	public FornecedorEntityDTO(FornecedorEntity obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cpfOuCnpj = obj.getCpfOuCnpj();
	}
}
