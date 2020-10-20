package br.com.searadivina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class EstadoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private Integer id;
	
	@Column(name = "ds_nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<CidadeEntity> cidades = new ArrayList<>();
	
	public EstadoEntity(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
