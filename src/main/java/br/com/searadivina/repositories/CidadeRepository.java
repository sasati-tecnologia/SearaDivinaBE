package br.com.searadivina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.CidadeEntity;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer> {
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM CidadeEntity obj where obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<CidadeEntity> findCidades(@Param("estadoId") Integer estado_id);
	
}
