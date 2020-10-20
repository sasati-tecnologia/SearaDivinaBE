package br.com.searadivina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.dto.CategoriaEntityDTO;
import br.com.searadivina.dto.CategoriaNewDTO;
import br.com.searadivina.model.CategoriaEntity;
import br.com.searadivina.repositories.CategoriaRepository;
import br.com.searadivina.services.exceptions.DataIntegrityException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public CategoriaEntity find(Integer id) {	
		Optional<CategoriaEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CategoriaEntity.class.getName()));
	}

	@Transactional
	public CategoriaEntity insert(CategoriaEntity obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public CategoriaEntity fromDTO(CategoriaNewDTO objDto) {
		CategoriaEntity cat = new CategoriaEntity(null, objDto.getNome());
		return cat;
	}
	
	public CategoriaEntity fromDTO(CategoriaEntityDTO objDto) {
		return new CategoriaEntity(objDto.getId(), objDto.getNome());
	}
	
	public CategoriaEntity update(CategoriaEntity obj) {
		CategoriaEntity newObj = find(obj.getId());
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
	
	public List<CategoriaEntity> findAll() {
		return repo.findAll();
	}
	
	private void updateData(CategoriaEntity newObj, CategoriaEntity obj) {
		newObj.setNome(obj.getNome());
	}
}
