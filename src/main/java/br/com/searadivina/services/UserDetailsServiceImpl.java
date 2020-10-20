package br.com.searadivina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.searadivina.model.AssociadoEntity;
import br.com.searadivina.repositories.AssociadoRepository;
import br.com.searadivina.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
 	@Autowired
	private AssociadoRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AssociadoEntity cli = repo.findByEmail(email);
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}