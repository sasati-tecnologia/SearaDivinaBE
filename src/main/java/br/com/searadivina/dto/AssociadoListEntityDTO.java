package br.com.searadivina.dto;

import java.io.Serializable;

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
public class AssociadoListEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	private String status;

	public AssociadoListEntityDTO(AssociadoEntity obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		status = obj.getStatus();
	}
}
