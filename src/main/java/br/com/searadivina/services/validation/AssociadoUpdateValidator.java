package br.com.searadivina.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.searadivina.dto.AssociadoEntityDTO;
import br.com.searadivina.exceptions.FieldMessage;
import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.repositories.AssociadoRepository;

public class AssociadoUpdateValidator implements ConstraintValidator<AssociadoUpdate, AssociadoEntityDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AssociadoRepository repoAssociado;

	@Override
	public void initialize(AssociadoUpdate ann) {
	}

	@Override
	public boolean isValid(AssociadoEntityDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		AssociadoEntity aux = repoAssociado.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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
