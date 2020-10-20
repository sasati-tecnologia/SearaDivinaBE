package br.com.searadivina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.searadivina.dto.EscolaridadeDTO;
import br.com.searadivina.dto.EstadoCivilDTO;
import br.com.searadivina.dto.StatusDTO;
import br.com.searadivina.dto.TipoSexoDTO;
import br.com.searadivina.services.EnumService;

@RestController
@RequestMapping(value="/enums")
public class EnumsController {
	
	@Autowired
	private EnumService service;
	
	@RequestMapping(value="/tiposexo", method=RequestMethod.GET)
	public ResponseEntity<List<TipoSexoDTO>> findSexo(){
		List<TipoSexoDTO> obj = service.findEnumSexo();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/status", method=RequestMethod.GET)
	public ResponseEntity<List<StatusDTO>> findStatus(){
		List<StatusDTO> obj = service.findEnumstatus();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/escolaridade", method=RequestMethod.GET)
	public ResponseEntity<List<EscolaridadeDTO>> findEscolaridade(){
		List<EscolaridadeDTO> obj = service.findEnumEscolaridade();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/estadocivil", method=RequestMethod.GET)
	public ResponseEntity<List<EstadoCivilDTO>> findEstadoCivil(){
		List<EstadoCivilDTO> obj = service.findEnumEstadoCivil();
		return ResponseEntity.ok().body(obj);
	}

}