package br.com.searadivina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.dto.FornecedorEntityDTO;
import br.com.searadivina.dto.FornecedorNewDTO;
import br.com.searadivina.enums.TipoPessoa;
import br.com.searadivina.model.FornecedorEntity;
import br.com.searadivina.repositories.FornecedorRepository;
import br.com.searadivina.services.exceptions.DataIntegrityException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repo;

	public FornecedorEntity find(Integer id) {	
		Optional<FornecedorEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + FornecedorEntity.class.getName()));
	}
	
	public FornecedorEntity fromDTO(FornecedorNewDTO objDto) {
		FornecedorEntity fornec = new FornecedorEntity(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoPessoa.toEnum(objDto.getTipo()));
		fornec.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			fornec.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			fornec.getTelefones().add(objDto.getTelefone3());
		}
		return fornec;
	}
	
	@Transactional
	public FornecedorEntity insert(FornecedorEntity obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public FornecedorEntity fromDTO(FornecedorEntityDTO objDto) {
		return new FornecedorEntity(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public FornecedorEntity update(FornecedorEntity obj) {
		FornecedorEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há registros relacionados");
		}
	}
	
	public List<FornecedorEntity> findAll() {
		return repo.findAll();
	}
	
	public Page<FornecedorEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(FornecedorEntity newObj, FornecedorEntity obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
