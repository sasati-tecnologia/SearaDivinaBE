package br.com.searadivina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.model.FornecedorEntity;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Integer>{

	@Transactional(readOnly=true)
	AssociadoEntity findByCpfOuCnpj(String cnpj);

	@Transactional(readOnly=true)
	FornecedorEntity findByEmail(String email);
}
