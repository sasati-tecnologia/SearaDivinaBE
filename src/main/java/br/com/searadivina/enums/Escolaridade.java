package br.com.searadivina.enums;

public enum Escolaridade {

	EDUCACAOINFANTIL(1, "Educação infantil"), 
	FUNDAMENTAL(2, "Fundamental"),
	MEDIO(3, "Médio"), 
	SUPERIOR(4, "Superior (Graduação)"),
	POSGRADUACAO(5, "Pós-graduação"), 
	MESTRADO(6, "Mestrado"),
	DOUTORADO(7, "Doutorado"), 
	POSDOUTORADO(8, "Pós-doutorado");

	private int cod;
	private String descricao;

	private Escolaridade(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Escolaridade toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (Escolaridade x : Escolaridade.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
