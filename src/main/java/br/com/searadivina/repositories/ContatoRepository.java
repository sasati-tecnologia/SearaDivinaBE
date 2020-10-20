package br.com.searadivina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Integer> {
	
	@Transactional(readOnly=true)
	ContatoEntity findByEmail(String email);
	
	@Transactional(readOnly=true)
	List<ContatoEntity> findAll();	
}
