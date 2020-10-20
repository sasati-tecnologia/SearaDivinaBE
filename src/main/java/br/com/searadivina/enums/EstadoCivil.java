package br.com.searadivina.enums;

public enum EstadoCivil {

	CASADO(1, "Casado"), 
	DIVORCIADO(2, "Divorciado"),
	SEPARADO(3, "Separado"), 
	SOLTEIRO(4, "Solteiro"),
	UNIAOESTAVEL(5, "União estável"), 
	VIUVO(6, "Viúvo");

	private int cod;
	private String descricao;

	private EstadoCivil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoCivil toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EstadoCivil x : EstadoCivil.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
