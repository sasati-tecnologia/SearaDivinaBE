package br.com.searadivina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.searadivina.enums.Perfil;
import br.com.searadivina.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class AssociadoEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_associado")
	private Integer id;
	
	@Column(name = "ds_nome")
	private String nome;
	
	@Column(name = "ds_sexo")
	private String sexo;
	
	@Column(name = "dt_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "nu_idade")
	private Integer idade;
	
	@Column(name = "ds_estadoCivil")
	private String estadoCivil;
	
	@Column(name = "nu_cpfOuCnpj")
	private String cpfOuCnpj;
	
	@Column(name = "nu_tipo")
	private Integer tipo;
	
	@Column(name = "nu_rg")
	private String rg;
	
	@Column(unique=true, name = "ds_email")
	private String email;
	
	@Column(name = "ds_profissao")
	private String profissao;
	
	@Column(name = "nu_escolaridade")
	private String escolaridade;
	
	@Column(name = "ds_status")
	private String status;
	
	@JsonIgnore
	@Column(name = "ds_senha")
	private String senha;
	
	@OneToMany(mappedBy="associado", cascade=CascadeType.ALL)
	private List<EnderecoEntity> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToOne(mappedBy="associado", cascade=CascadeType.ALL)
	private AnamineseEntity anaminese = new AnamineseEntity();
	
	public AssociadoEntity() {
		addPerfil(Perfil.ASSOCIADO);
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public AssociadoEntity(Integer id, String nome, String email, String cpfOuCnpj, TipoPessoa tipo, String status, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null)? null: tipo.getCod();
		this.status = status;
		this.senha = senha;
		addPerfil(Perfil.ASSOCIADO);
	}

	public AssociadoEntity(Integer id, String nome, String sexo, LocalDate dataNascimento, Integer idade,
			String estadoCivil, String cpfOuCnpj, TipoPessoa tipo, String rg, String email, String profissao,
			String escolaridade, String status, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.estadoCivil = estadoCivil;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null)? null: tipo.getCod();
		this.rg = rg;
		this.email = email;
		this.profissao = profissao;
		this.escolaridade = escolaridade;
		this.status = status;
		this.senha = senha;
		addPerfil(Perfil.ASSOCIADO);
	}	
}
