package br.com.searadivina.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EscolaridadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer cod;
	private String descricao;
}