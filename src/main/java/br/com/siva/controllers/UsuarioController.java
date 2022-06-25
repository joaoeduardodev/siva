package br.com.siva.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.siva.domains.Usuario;
import br.com.siva.repositories.UsuarioRepositorio;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@GetMapping("/listarUsuario")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("usuarios");
		mv.addObject("usuarios", usuarioRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarUsuario")
	public ModelAndView cadastrar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/salvarUsuario")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(usuario);
		}
		
		if(usuario.getSenha().length() < 20) {
			usuario.setSenha( new BCryptPasswordEncoder().encode(usuario.getSenha()));
		}
		
		usuarioRepo.saveAndFlush(usuario);
		

		return new ModelAndView("redirect:/listarUsuario");
	}
	
	@GetMapping("/editarUsuario/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepo.findById(id);
		
		return cadastrar(usuario.get());		
	}
	
	@GetMapping("/excluirUsuario/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepo.findById(id);
		usuarioRepo.delete(usuario.get());
		
		return new ModelAndView("redirect:/listarUsuario");
	}
}
