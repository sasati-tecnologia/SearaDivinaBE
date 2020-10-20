package br.com.searadivina.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.searadivina.dto.EscolaridadeDTO;
import br.com.searadivina.dto.EstadoCivilDTO;
import br.com.searadivina.dto.StatusDTO;
import br.com.searadivina.dto.TipoSexoDTO;
import br.com.searadivina.enums.Escolaridade;
import br.com.searadivina.enums.EstadoCivil;
import br.com.searadivina.enums.Status;
import br.com.searadivina.enums.TipoSexo;

@Service
public class EnumService {

	public List<TipoSexoDTO> findEnumSexo() {
		List<TipoSexoDTO> list = new ArrayList<>();
		TipoSexoDTO tipo1 = new TipoSexoDTO();
		tipo1.setCod(TipoSexo.MASCULINO.getCod());
		tipo1.setDescricao(TipoSexo.MASCULINO.getDescricao());
		TipoSexoDTO tipo2 = new TipoSexoDTO();
		tipo2.setCod(TipoSexo.FEMININO.getCod());
		tipo2.setDescricao(TipoSexo.FEMININO.getDescricao());
		list.addAll(Arrays.asList(tipo2,tipo1));
		return list;
	}
	
	public List<StatusDTO> findEnumstatus() {
		List<StatusDTO> list = new ArrayList<>();
		StatusDTO tipo1 = new StatusDTO();
		tipo1.setCod(Status.ATIVO.getCod());
		tipo1.setDescricao(Status.ATIVO.getDescricao());
		StatusDTO tipo2 = new StatusDTO();
		tipo2.setCod(Status.INATIVO.getCod());
		tipo2.setDescricao(Status.INATIVO.getDescricao());
		list.addAll(Arrays.asList(tipo1,tipo2));
		return list;
	}

	public List<EscolaridadeDTO> findEnumEscolaridade() {
		List<EscolaridadeDTO> list = new ArrayList<>();
		EscolaridadeDTO tipo1 = new EscolaridadeDTO();
		tipo1.setCod(Escolaridade.EDUCACAOINFANTIL.getCod());
		tipo1.setDescricao(Escolaridade.EDUCACAOINFANTIL.getDescricao());
		EscolaridadeDTO tipo2 = new EscolaridadeDTO();
		tipo2.setCod(Escolaridade.FUNDAMENTAL.getCod());
		tipo2.setDescricao(Escolaridade.FUNDAMENTAL.getDescricao());
		EscolaridadeDTO tipo3 = new EscolaridadeDTO();
		tipo3.setCod(Escolaridade.MEDIO.getCod());
		tipo3.setDescricao(Escolaridade.MEDIO.getDescricao());
		EscolaridadeDTO tipo4 = new EscolaridadeDTO();
		tipo4.setCod(Escolaridade.SUPERIOR.getCod());
		tipo4.setDescricao(Escolaridade.SUPERIOR.getDescricao());
		EscolaridadeDTO tipo5 = new EscolaridadeDTO();
		tipo5.setCod(Escolaridade.POSGRADUACAO.getCod());
		tipo5.setDescricao(Escolaridade.POSGRADUACAO.getDescricao());
		EscolaridadeDTO tipo6 = new EscolaridadeDTO();
		tipo6.setCod(Escolaridade.MESTRADO.getCod());
		tipo6.setDescricao(Escolaridade.MESTRADO.getDescricao());
		EscolaridadeDTO tipo7 = new EscolaridadeDTO();
		tipo7.setCod(Escolaridade.DOUTORADO.getCod());
		tipo7.setDescricao(Escolaridade.DOUTORADO.getDescricao());
		EscolaridadeDTO tipo8 = new EscolaridadeDTO();
		tipo8.setCod(Escolaridade.POSDOUTORADO.getCod());
		tipo8.setDescricao(Escolaridade.POSDOUTORADO.getDescricao());
		list.addAll(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6,tipo7,tipo8));
		return list;
	}

	public List<EstadoCivilDTO> findEnumEstadoCivil() {
		List<EstadoCivilDTO> list = new ArrayList<>();
		EstadoCivilDTO tipo1 = new EstadoCivilDTO();
		tipo1.setCod(EstadoCivil.CASADO.getCod());
		tipo1.setDescricao(EstadoCivil.CASADO.getDescricao());
		EstadoCivilDTO tipo2 = new EstadoCivilDTO();
		tipo2.setCod(EstadoCivil.DIVORCIADO.getCod());
		tipo2.setDescricao(EstadoCivil.DIVORCIADO.getDescricao());
		EstadoCivilDTO tipo3 = new EstadoCivilDTO();
		tipo3.setCod(EstadoCivil.SEPARADO.getCod());
		tipo3.setDescricao(EstadoCivil.SEPARADO.getDescricao());
		EstadoCivilDTO tipo4 = new EstadoCivilDTO();
		tipo4.setCod(EstadoCivil.SOLTEIRO.getCod());
		tipo4.setDescricao(EstadoCivil.SOLTEIRO.getDescricao());
		EstadoCivilDTO tipo5 = new EstadoCivilDTO();
		tipo5.setCod(EstadoCivil.UNIAOESTAVEL.getCod());
		tipo5.setDescricao(EstadoCivil.UNIAOESTAVEL.getDescricao());
		EstadoCivilDTO tipo6 = new EstadoCivilDTO();
		tipo6.setCod(EstadoCivil.VIUVO.getCod());
		tipo6.setDescricao(EstadoCivil.VIUVO.getDescricao());
		list.addAll(Arrays.asList(tipo1,tipo2,tipo3,tipo4,tipo5,tipo6));
		return list;
	}
}
