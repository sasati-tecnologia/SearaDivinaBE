package br.com.searadivina.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.searadivina.dto.AssociadoEntityDTO;
import br.com.searadivina.dto.AssociadoListEntityDTO;
import br.com.searadivina.dto.AssociadoNewDTO;
import br.com.searadivina.enums.Perfil;
import br.com.searadivina.enums.TipoPessoa;
import br.com.searadivina.model.AnamineseEntity;
import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.model.CidadeEntity;
import br.com.searadivina.model.EnderecoEntity;
import br.com.searadivina.repositories.AnamineseRepository;
import br.com.searadivina.repositories.AssociadoRepository;
import br.com.searadivina.repositories.EnderecoRepository;
import br.com.searadivina.security.UserSS;
import br.com.searadivina.services.exceptions.AuthorizationException;
import br.com.searadivina.services.exceptions.DataIntegrityException;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private AnamineseRepository anamineseRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public AssociadoEntity find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<AssociadoEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AssociadoEntity.class.getName()));
	}
	
	@Transactional
	public AssociadoEntity insert(AssociadoEntity obj) {
		obj.setId(null);
		obj.setStatus("Ativo");
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		anamineseRepository.save(obj.getAnaminese());
		return obj;
	}
	
	public AssociadoEntity update(AssociadoEntity obj) {
		AssociadoEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
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
	
	public List<AssociadoListEntityDTO> findAll() {
		List<AssociadoEntity> list = repo.findAll();
		List<AssociadoListEntityDTO> listDto = list.stream().map(obj -> new AssociadoListEntityDTO(obj)).collect(Collectors.toList());  
		return listDto;
	}
	
	public AssociadoEntity findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())){
			throw new AuthorizationException("Acesso Negado");
		}
		
		AssociadoEntity obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + AssociadoEntity.class.getName());
		}
		return obj;
	}
	
	public Page<AssociadoEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public AssociadoEntity fromDTO(AssociadoEntityDTO objDto) {
		return new AssociadoEntity(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, null);
	}
	
	public AssociadoEntity fromDTO(AssociadoNewDTO objDto) {
		AssociadoEntity cli = new AssociadoEntity(
				null, objDto.getNome(),
				objDto.getSexo(), objDto.getDataNascimento(),
				objDto.getIdade(), objDto.getEstadoCivil(),
				objDto.getCpfOuCnpj(), TipoPessoa.toEnum(objDto.getTipo()), 
				objDto.getRg(),	objDto.getEmail(),  
				objDto.getProfissao(), objDto.getEscolaridade(),
				objDto.getStatus(), pe.encode(objDto.getSenha()));
		CidadeEntity cid = new CidadeEntity(objDto.getCidadeId(), null, null);
		EnderecoEntity end = new EnderecoEntity(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		AnamineseEntity ana = new AnamineseEntity(null, objDto.getSobreSeara(), objDto.getExpectativaSeara(), objDto.getContatoNome(), objDto.getContatoTelefone(), objDto.getContatoNome2(), objDto.getContatoTelefone2(),
				objDto.getRestricaoFisica(), objDto.getRfDetalhes(), objDto.getProblemaSaude(), objDto.getPsDetalhes(), objDto.getMedicamento(), objDto.getMedicamentoDetalhes(), objDto.getContatoMedicoNome(), 
				objDto.getContatoMedicoEndereco(), objDto.getContatoMedicoTelefone(), objDto.getRestricaoAlimentar(), objDto.getRaDetalhes(), objDto.getAspectoEmocional(), objDto.getDeprimido(), objDto.getAnsioso(), 
				objDto.getTratamentoPsicologico(), objDto.getTpPeriodo(), objDto.getPsicologoNome(), objDto.getUsoIntorpecentes(), objDto.getUiTipo(), objDto.getUiPeriodo(), objDto.getObservacoes(), cli);
		cli.setAnaminese(ana);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(AssociadoEntity newObj, AssociadoEntity obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
