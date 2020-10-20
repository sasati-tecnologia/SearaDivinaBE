package br.com.searadivina.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.searadivina.dto.CidadeEntityDTO;
import br.com.searadivina.dto.EstadoEntityDTO;
import br.com.searadivina.model.CidadeEntity;
import br.com.searadivina.model.EstadoEntity;
import br.com.searadivina.services.CidadeService;
import br.com.searadivina.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoEntityDTO>> findAll() {
		List<EstadoEntity> list = service.findAll();
		List<EstadoEntityDTO> listDto = list.stream().map(obj -> new EstadoEntityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeEntityDTO>> findCidades(@PathVariable Integer estadoId) {
		List<CidadeEntity> list = cidadeService.findByEstado(estadoId);
		List<CidadeEntityDTO> listDto = list.stream().map(obj -> new CidadeEntityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
