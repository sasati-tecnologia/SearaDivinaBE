package br.com.searadivina.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.searadivina.dto.ContatoEntityDTO;
import br.com.searadivina.model.ContatoEntity;
import br.com.searadivina.services.ContatoService;

@RestController
@RequestMapping(value="/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ContatoEntity> find(@PathVariable Integer id) {
		ContatoEntity obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<ContatoEntity> find(@RequestParam(value="value") String email) {
		ContatoEntity obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ContatoEntityDTO objDto) {
		ContatoEntity obj = new ContatoEntity();
		obj.setNome(objDto.getNome());
		obj.setEmail(objDto.getEmail());
		obj.setMensagem(objDto.getMensagem());
		obj.setDataInclusao(new Date());
		obj.setStatus(true);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContatoEntityDTO>> findAll() {
		List<ContatoEntity> list = service.findAll();
		List<ContatoEntityDTO> listDto = list.stream().map(obj -> new ContatoEntityDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ContatoEntityDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<ContatoEntity> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ContatoEntityDTO> listDto = list.map(obj -> new ContatoEntityDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}