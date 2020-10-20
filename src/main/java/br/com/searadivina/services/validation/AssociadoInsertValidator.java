package br.com.searadivina.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.searadivina.dto.AssociadoNewDTO;
import br.com.searadivina.enums.TipoPessoa;
import br.com.searadivina.exceptions.FieldMessage;
import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.repositories.AssociadoRepository;
import br.com.searadivina.services.validation.utils.BR;

public class AssociadoInsertValidator implements ConstraintValidator<AssociadoInsert, AssociadoNewDTO> {
	
	@Autowired
	private AssociadoRepository repoAssociado;
	
	@Override
	public void initialize(AssociadoInsert ann) {
	}

	@Override
	public boolean isValid(AssociadoNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoPessoa.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
 		if (objDto.getTipo().equals(TipoPessoa.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
 		
 		AssociadoEntity aux = repoAssociado.findByEmail(objDto.getEmail());
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