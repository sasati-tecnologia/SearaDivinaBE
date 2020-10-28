package br.com.searadivina.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.searadivina.dto.AssociadoEntityDTO;
import br.com.searadivina.dto.AssociadoListEntityDTO;
import br.com.searadivina.dto.AssociadoNewDTO;
import br.com.searadivina.model.AnamineseEntity;
import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.services.AnamineseService;
import br.com.searadivina.services.AssociadoService;

@RestController
@RequestMapping(value="/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@Autowired
	private AnamineseService anamineseService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AssociadoEntity> find(@PathVariable Integer id) {
		AssociadoEntity obj = service.find(id);
		return ResponseEntity.ok().body(obj); 
	}
	
	@RequestMapping(value="/anaminese/{id}", method=RequestMethod.GET)
	public ResponseEntity<AnamineseEntity> findAnaminese(@PathVariable Integer id) {
		AnamineseEntity obj = anamineseService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<AssociadoEntity> find(@RequestParam(value="value") String email) {
		AssociadoEntity obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody AssociadoNewDTO objDto) {
		AssociadoEntity obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AssociadoEntityDTO objDto, @PathVariable Integer id) {
		AssociadoEntity obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<AssociadoListEntityDTO>> findAll() {
		List<AssociadoListEntityDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<AssociadoEntityDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<AssociadoEntity> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AssociadoEntityDTO> listDto = list.map(obj -> new AssociadoEntityDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}