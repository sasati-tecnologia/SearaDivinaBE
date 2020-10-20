package br.com.searadivina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.searadivina.model.EstadoEntity;
import br.com.searadivina.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<EstadoEntity> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	
}
