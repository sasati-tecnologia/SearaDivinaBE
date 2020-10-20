package br.com.searadivina.enums;

public enum TipoSexo {

	FEMININO(1, "Feminino"), 
	MASCULINO(2, "Masculino");

	private int cod;
	private String descricao;

	private TipoSexo(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoSexo toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (TipoSexo x : TipoSexo.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
