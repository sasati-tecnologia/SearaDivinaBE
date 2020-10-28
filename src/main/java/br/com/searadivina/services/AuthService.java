package br.com.searadivina.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.searadivina.services.exceptions.ObjectNotFoundException;
import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.repositories.AssociadoRepository;


@Service
public class AuthService {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		AssociadoEntity associado = associadoRepository.findByEmail(email);
		if (associado == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		associado.setSenha(pe.encode(newPass));
		
		associadoRepository.save(associado);
		emailService.sendNewPasswordEmail(associado, newPass);
	}
	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}
	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}