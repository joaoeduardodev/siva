package br.com.siva.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.siva.domains.Permissao;
import br.com.siva.domains.Role;
import br.com.siva.domains.Usuario;
import br.com.siva.repositories.PermissaoRepositorio;
import br.com.siva.repositories.RoleRepositorio;
import br.com.siva.repositories.UsuarioRepositorio;

@Component
public class Migration implements CommandLineRunner {

	@Autowired
	private RoleRepositorio roleRepository;

	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Autowired
	private PermissaoRepositorio permissaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Role role1 = new Role();
		role1.setNome("authority");
		Role role2 = new Role();
		role2.setNome("cidade");
		Role role3 = new Role();
		role3.setNome("acesso");
		Role role4 = new Role();
		role4.setNome("role");
		Role role5 = new Role();
		role5.setNome("usuario");
		Role role6 = new Role();
		role6.setNome("estado");
		Role role7 = new Role();
		role7.setNome("empresa");
		Role role8 = new Role();
		role8.setNome("vaga");
		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role3);
		roleRepository.save(role4);
		roleRepository.save(role5);
		roleRepository.save(role6);
		roleRepository.save(role7);
		roleRepository.save(role8);
		
		Usuario usuario1 = new Usuario();
		usuario1.setNome("admin");
		usuario1.setSenha(new BCryptPasswordEncoder().encode("123"));
		usuarioRepository.save(usuario1);
		
		Permissao permissao1 = new Permissao();
		permissao1.setRole(role1);
		permissao1.setUsuario(usuario1);
		Permissao permissao2 = new Permissao();
		permissao2.setRole(role2);
		permissao2.setUsuario(usuario1);
		Permissao permissao3 = new Permissao();
		permissao3.setRole(role3);
		permissao3.setUsuario(usuario1);
		Permissao permissao4 = new Permissao();
		permissao4.setRole(role4);
		permissao4.setUsuario(usuario1);
		Permissao permissao5 = new Permissao();
		permissao5.setRole(role5);
		permissao5.setUsuario(usuario1);
		Permissao permissao6 = new Permissao();
		permissao6.setRole(role6);
		permissao6.setUsuario(usuario1);
		Permissao permissao7 = new Permissao();
		permissao7.setRole(role7);
		permissao7.setUsuario(usuario1);
		Permissao permissao8 = new Permissao();
		permissao8.setRole(role8);
		permissao8.setUsuario(usuario1);
		permissaoRepository.save(permissao1);
		permissaoRepository.save(permissao2);
		permissaoRepository.save(permissao3);
		permissaoRepository.save(permissao4);
		permissaoRepository.save(permissao5);
		permissaoRepository.save(permissao6);
		permissaoRepository.save(permissao7);
		permissaoRepository.save(permissao8);
		
	}

}
