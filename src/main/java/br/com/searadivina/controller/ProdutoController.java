package br.com.searadivina.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.searadivina.dto.ProdutoEntityDTO;
import br.com.searadivina.dto.ProdutoEntityNewDTO;
import br.com.searadivina.dto.ProdutoListResponseDTO;
import br.com.searadivina.dto.ProdutoSearchDTO;
import br.com.searadivina.model.ProdutoEntity;
import br.com.searadivina.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoEntityNewDTO objDto) {
		ProdutoEntity obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoEntityDTO objDto, @PathVariable Integer id) {
		ProdutoEntity obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<ProdutoListResponseDTO> findAll() {
		ProdutoListResponseDTO list = service.findAllDTO();
		//List<ProdutoListEntityDTO> list = service.findAllDTO();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/search", method=RequestMethod.GET)
	public ResponseEntity<ProdutoListResponseDTO> findAllSearch(
			@RequestParam(value="modalidade")String modalidade,
			@RequestParam(value="dtInicio")String dtInicio,
			@RequestParam(value="dtFinal")String dtFinal) {
			ProdutoSearchDTO objDto = new ProdutoSearchDTO();
			objDto.setModalidade(modalidade);
			objDto.setDtInicio(dtInicio);
			objDto.setDtFinal(dtFinal);
			ProdutoListResponseDTO list = service.findAllSearch(objDto);
		return ResponseEntity.ok().body(list);
	}
}