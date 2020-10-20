package br.com.searadivina.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.searadivina.enums.Perfil;
import br.com.searadivina.model.AnamineseEntity;
import br.com.searadivina.repositories.AnamineseRepository;
import br.com.searadivina.security.UserSS;
import br.com.searadivina.services.exceptions.AuthorizationException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;

@Service
public class AnamineseService {

	@Autowired
	private AnamineseRepository anamineseRepository;
	
	public AnamineseEntity find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<AnamineseEntity> obj = anamineseRepository.findByAssociado(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AnamineseEntity.class.getName()));
	}

	public AnamineseEntity update(AnamineseEntity obj) {
		AnamineseEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return anamineseRepository.save(newObj);
	}
	
	private void updateData(AnamineseEntity newObj, AnamineseEntity obj) {
		newObj.setSobreSeara(obj.getSobreSeara());
		newObj.setExpectativaSeara(obj.getExpectativaSeara());
		newObj.setContatoNome(obj.getContatoNome());
		newObj.setContatoTelefone(obj.getContatoTelefone());
		newObj.setContatoNome2(obj.getContatoNome2());
		newObj.setContatoTelefone2(obj.getContatoTelefone2());
		newObj.setRestricaoFisica(obj.getRestricaoFisica());
		newObj.setRfDetalhes(obj.getRfDetalhes());
		newObj.setProblemaSaude(obj.getProblemaSaude());
		newObj.setPsDetalhes(obj.getPsDetalhes());
		newObj.setMedicamento(obj.getMedicamento());
		newObj.setMedicamentoDetalhes(obj.getMedicamentoDetalhes());
		newObj.setContatoMedicoNome(obj.getContatoMedicoNome());
		newObj.setContatoMedicoEndereco(obj.getContatoMedicoEndereco());
		newObj.setContatoMedicoTelefone(obj.getContatoMedicoTelefone());
		newObj.setRestricaoAlimentar(obj.getRestricaoAlimentar());
		newObj.setRaDetalhes(obj.getRaDetalhes());
		newObj.setAspectoEmocional(obj.getAspectoEmocional());
		newObj.setDeprimido(obj.getDeprimido());
		newObj.setAnsioso(obj.getAnsioso());
		newObj.setTratamentoPsicologico(obj.getTratamentoPsicologico());
		newObj.setTpPeriodo(obj.getTpPeriodo());
		newObj.setPsicologoNome(obj.getPsicologoNome());
		newObj.setUsoIntorpecentes(obj.getUsoIntorpecentes());
		newObj.setUiTipo(obj.getUiTipo());
		newObj.setUiPeriodo(obj.getUiPeriodo());
		newObj.setObservacoes(obj.getObservacoes());
	}
}
