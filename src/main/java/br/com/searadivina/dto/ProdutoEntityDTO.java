package br.com.searadivina.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import br.com.searadivina.model.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntityDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private Integer categoria;
	
	private Integer associado;

	private Integer fornecedor;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String produto;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String pagamento;

	@NotEmpty(message = "Preenchimento obrigatório")
	private LocalDate vencimento;

	private LocalDate lancamento;

	@NotEmpty(message = "Preenchimento obrigatório")
	private Double valor;

	private Double taxa;

	private Double juros;
	
	public ProdutoEntityDTO(ProdutoEntity obj) {
		id = obj.getId();
		categoria = obj.getCategoria();
		associado = obj.getAssociado();
		fornecedor = obj.getFornecedor();
		produto = obj.getProduto();
		pagamento = obj.getPagamento();
		vencimento = obj.getVencimento();
		lancamento = obj.getLancamento();
		valor = obj.getValor();
		taxa = obj.getTaxa();
		juros = obj.getJuros();
	}
}
