package br.com.searadivina.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoSearchDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String modalidade;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String dtInicio;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String dtFinal;	
}
