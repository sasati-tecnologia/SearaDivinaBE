package br.com.searadivina.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.searadivina.dto.ProdutoEntityDTO;
import br.com.searadivina.dto.ProdutoEntityNewDTO;
import br.com.searadivina.dto.ProdutoListEntityDTO;
import br.com.searadivina.dto.ProdutoListResponseDTO;
import br.com.searadivina.dto.ProdutoSearchDTO;
import br.com.searadivina.model.ProdutoEntity;
import br.com.searadivina.repositories.ProdutoRepository;
import br.com.searadivina.services.exceptions.DataIntegrityException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;
import br.com.searadivina.utils.FormatUtils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public ProdutoEntity find(Integer id) {	
		Optional<ProdutoEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + ProdutoEntity.class.getName()));
	}
	
	@Transactional
	public ProdutoEntity insert(ProdutoEntity obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public ProdutoEntity update(ProdutoEntity obj) {
		ProdutoEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	
	private void updateData(ProdutoEntity newObj, ProdutoEntity obj) {
		newObj.setAssociado(obj.getAssociado());
		newObj.setFornecedor(obj.getFornecedor());
		newObj.setProduto(obj.getProduto());
		newObj.setPagamento(obj.getPagamento());
		newObj.setVencimento(obj.getVencimento());
		newObj.setLancamento(obj.getLancamento());
		newObj.setValor(obj.getValor());
		newObj.setTaxa(obj.getTaxa());
		newObj.setJuros(obj.getJuros());
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
	
	public List<ProdutoEntity> findAll() {
		return repo.findAll();
	}
	
	public ProdutoListResponseDTO findAllDTO() {
		List<Object[]> listObj = repo.findAllDTO();
		List<ProdutoListEntityDTO> list = new ArrayList<>();
		listObj.forEach(line -> list.add(new ProdutoListEntityDTO(line)));
		ProdutoListResponseDTO response = preencheResponse(list);
		return response;
	}


	
	public ProdutoEntity fromDTO(ProdutoEntityDTO objDto) {
		ProdutoEntity prd = new ProdutoEntity(null, objDto.getCategoria(), 
				objDto.getAssociado(), objDto.getFornecedor(), objDto.getProduto(),
				objDto.getPagamento(), objDto.getVencimento(), objDto.getLancamento(),
				objDto.getValor(), objDto.getTaxa(), objDto.getJuros());
		return prd;
	}
	
	public ProdutoEntity fromDTO(ProdutoEntityNewDTO objDto) {
		ProdutoEntity prd = new ProdutoEntity(null, objDto.getCategoria(), 
				objDto.getAssociado(), objDto.getFornecedor(), objDto.getProduto(),
				objDto.getPagamento(), convertDate(objDto.getVencimento()), convertDate(objDto.getLancamento()),
				converteDouble(objDto.getValor()), converteDouble(objDto.getTaxa()), converteDouble(objDto.getJuros()));
		return prd;
	}

	private Double converteDouble(String string) {
		String str = string.replace(",", "."); 
		return Double.valueOf(str).doubleValue();
	}
	
	private LocalDate convertDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
	            .withLocale(new Locale("pt", "BR"));
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}

	public ProdutoListResponseDTO findAllSearch(ProdutoSearchDTO objDto) {
		if(objDto.getModalidade().equals("Ambos"))objDto.setModalidade("%ito");
			
		List<Object[]> listObj = repo.findAllSearch(objDto.getModalidade(), objDto.getDtInicio(), objDto.getDtFinal());
		List<ProdutoListEntityDTO> list = new ArrayList<>();
		listObj.forEach(line -> list.add(new ProdutoListEntityDTO(line)));
		ProdutoListResponseDTO response = preencheResponse(list);
		return response;
	}
	
	private ProdutoListResponseDTO preencheResponse(List<ProdutoListEntityDTO> list) {
		ProdutoListResponseDTO response = new ProdutoListResponseDTO();
		response.setListaProduto(list);
		if(response.getListaProduto().isEmpty()) {
			response.setTotalCredito(0.00);
			response.setTotalDebito(0.00);
			response.setTotalGeral(0.00);
		}else {
			double cred = 0.00;
			double deb = 0.00;
			for (ProdutoListEntityDTO produtoList : list) {
				if(produtoList.getProduto().equals("Crédito")) {
					cred += produtoList.getValor(); 
				}else {
					deb += produtoList.getValor();
				}
			}
			response.setTotalCredito(cred);
			response.setTotalDebito(deb);
			response.setTotalGeral(FormatUtils.formataDouble(cred-deb));
		}
		return response;
	}



}
