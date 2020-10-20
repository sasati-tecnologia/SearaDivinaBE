package br.com.searadivina.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.searadivina.dto.FornecedorNewDTO;
import br.com.searadivina.enums.TipoPessoa;
import br.com.searadivina.exceptions.FieldMessage;
import br.com.searadivina.model.FornecedorEntity;
import br.com.searadivina.repositories.FornecedorRepository;
import br.com.searadivina.services.validation.utils.BR;

public class FornecedorInsertValidator implements ConstraintValidator<FornecedorInsert, FornecedorNewDTO> {
	
	@Autowired
	private FornecedorRepository repoFornecedor;
	
	@Override
	public void initialize(FornecedorInsert ann) {
	}

	@Override
	public boolean isValid(FornecedorNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoPessoa.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
 		if (objDto.getTipo().equals(TipoPessoa.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
 		
 		FornecedorEntity aux = repoFornecedor.findByEmail(objDto.getEmail());
 		if(aux != null) {
 			list.add(new FieldMessage("email", "Email já existente"));
 		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}