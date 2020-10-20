package br.com.searadivina.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.AnamineseEntity;

@Repository
public interface AnamineseRepository extends JpaRepository<AnamineseEntity, Integer>{

	@Transactional(readOnly=true)
	Optional<AnamineseEntity> findByAssociado(Integer id);
}
