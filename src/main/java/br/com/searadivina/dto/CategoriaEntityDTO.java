package br.com.searadivina.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.searadivina.model.CategoriaEntity;
import br.com.searadivina.services.validation.FornecedorUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FornecedorUpdate
public class CategoriaEntityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	public CategoriaEntityDTO(CategoriaEntity obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
}
