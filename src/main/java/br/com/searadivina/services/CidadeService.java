package br.com.searadivina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.searadivina.model.CidadeEntity;
import br.com.searadivina.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<CidadeEntity> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
	
	
}
