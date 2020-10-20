package br.com.searadivina.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoListEntityDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String categoria;
	private String associado;
	private String fornecedor;
	private String produto;
	private String pagamento;
	private String vencimento;
	private String lancamento;
	private Double valor;
	private Double taxa;
	private Double juros;
	
	public ProdutoListEntityDTO(Object[] obj) {
		this.id = Integer.valueOf(obj[0].toString());
		this.categoria = String.valueOf(obj[1]);
		this.associado = String.valueOf(obj[2]).toString().equals("null")?String.valueOf("Não se aplica"):String.valueOf(obj[2]);
		this.fornecedor = String.valueOf(obj[3]).toString().equals("null")?String.valueOf("Não se aplica"):String.valueOf(obj[3]);
		this.produto = String.valueOf(obj[4]);
		this.pagamento = String.valueOf(obj[5]);
		this.vencimento = String.valueOf(obj[6].toString().substring(8,10)+"/"+obj[6].toString().substring(5,7)+"/"+obj[6].toString().substring(0,4));
		this.lancamento = String.valueOf(obj[7].toString().substring(8,10)+"/"+obj[7].toString().substring(5,7)+"/"+obj[7].toString().substring(0,4));
		this.valor = (Double) obj[8];
		this.taxa = obj[9] == null ? null:((Double) (obj[9])).doubleValue();
		this.juros = obj[10] == null ? null:((Double) (obj[10])).doubleValue();;
	}

}
