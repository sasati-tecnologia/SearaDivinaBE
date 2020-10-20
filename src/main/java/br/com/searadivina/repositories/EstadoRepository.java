package br.com.searadivina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.EstadoEntity;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer> {
	
	@Transactional(readOnly=true)
	public List<EstadoEntity> findAllByOrderByNome();
}
