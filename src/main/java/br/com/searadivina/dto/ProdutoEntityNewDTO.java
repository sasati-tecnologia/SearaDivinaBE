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
public class ProdutoEntityNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer categoria;
	
	private Integer associado;

	private Integer fornecedor;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String produto;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String pagamento;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String vencimento;

	private String lancamento;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String valor;

	private String taxa;

	private String juros;	
}
