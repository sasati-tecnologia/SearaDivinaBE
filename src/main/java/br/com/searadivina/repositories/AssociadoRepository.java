package br.com.searadivina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.AssociadoEntity;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Integer>{

	@Transactional(readOnly=true)
	AssociadoEntity findByEmail(String email);
}
