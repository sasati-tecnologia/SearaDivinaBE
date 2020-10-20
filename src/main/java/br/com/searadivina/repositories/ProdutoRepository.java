package br.com.searadivina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.model.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

	@Transactional(readOnly=true)
	@Query(value="SELECT " + 
			"	p.id_produto as id, " + 
			"	c.ds_nome as categoria, " + 
			"	a.ds_nome as associado, " + 
			"	f.ds_nome as fornecedor, " + 
			"	p.ds_produto as produto, " + 
			"	p.ds_pagamento as pagamento, " + 
			"	p.dt_vencimento as vencimento, " + 
			"	p.dt_lancamento as lancamento, " + 
			"	p.vl_pagamento as valor, " + 
			"	p.vl_taxa as taxa, " + 
			"	p.vl_juros as juros " + 
			"FROM `produto_entity` p " + 
			"Left JOIN categoria_entity c ON " + 
			"p.id_categoria = c.id_categoria " + 
			"Left JOIN associado_entity a ON " + 
			"p.id_associado = a.id_associado " + 
			"Left JOIN fornecedor_entity f ON " + 
			"p.id_fornecedor = f.id_fornecedor", nativeQuery =  true)
	List<Object[]> findAllDTO();

	@Query(value="SELECT " + 
			"	p.id_produto as id, " + 
			"	c.ds_nome as categoria, " + 
			"	a.ds_nome as associado, " + 
			"	f.ds_nome as fornecedor, " + 
			"	p.ds_produto as produto, " + 
			"	p.ds_pagamento as pagamento, " + 
			"	p.dt_vencimento as vencimento, " + 
			"	p.dt_lancamento as lancamento, " + 
			"	p.vl_pagamento as valor, " + 
			"	p.vl_taxa as taxa, " + 
			"	p.vl_juros as juros " + 
			"FROM `produto_entity` p " + 
			"Left JOIN categoria_entity c ON " + 
			"p.id_categoria = c.id_categoria " + 
			"Left JOIN associado_entity a ON " + 
			"p.id_associado = a.id_associado " + 
			"Left JOIN fornecedor_entity f ON " + 
			"p.id_fornecedor = f.id_fornecedor " +
			"WHERE p.ds_produto like :produto and " +
			"p.dt_vencimento BETWEEN (:dtInicio) AND (:dtFinal)", nativeQuery =  true)
	List<Object[]> findAllSearch(
			String produto, 
			String dtInicio, 
			String dtFinal);
}
