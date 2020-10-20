package br.com.searadivina.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.enums.Perfil;
import br.com.searadivina.model.ContatoEntity;
import br.com.searadivina.repositories.ContatoRepository;
import br.com.searadivina.security.UserSS;
import br.com.searadivina.services.exceptions.AuthorizationException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository repo;
	
	public ContatoEntity find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<ContatoEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContatoEntity.class.getName()));
	}
	
	@Transactional
	public ContatoEntity insert(ContatoEntity obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public ContatoEntity update(ContatoEntity obj) {
		ContatoEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public List<ContatoEntity> findAll() {
		return repo.findAll();
	}
	
	public ContatoEntity findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())){
			throw new AuthorizationException("Acesso Negado");
		}
		
		ContatoEntity obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + ContatoEntity.class.getName());
		}
		return obj;
	}
	
	public Page<ContatoEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private void updateData(ContatoEntity newObj, ContatoEntity obj) {
		newObj.setStatus(obj.isStatus());
	}
}