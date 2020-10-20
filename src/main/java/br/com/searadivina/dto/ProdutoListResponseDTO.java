package br.com.searadivina.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoListResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Double totalCredito;
	private Double totalDebito;
	private Double totalGeral;
	private List<ProdutoListEntityDTO> listaProduto;
}
