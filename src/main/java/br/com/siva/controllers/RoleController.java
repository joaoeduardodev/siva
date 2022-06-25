package br.com.siva.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.siva.domains.Role;
import br.com.siva.repositories.RoleRepositorio;


@Controller
public class RoleController {
	
	@Autowired
	private RoleRepositorio roleRepo;
	
	@GetMapping("/listarRole")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("roles");
		mv.addObject("roles", roleRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarRole")
	public ModelAndView cadastrar(Role role) {
		ModelAndView mv = new ModelAndView("role");
		mv.addObject("role", role);
		
		return mv;
	}
	
	@PostMapping("/salvarRole")
	public ModelAndView salvar(@Valid Role role, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(role);
		}
		
		roleRepo.saveAndFlush(role);

		return new ModelAndView("redirect:/listarRole");
	}
	
	@GetMapping("/editarRole/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Role> role = roleRepo.findById(id);
		
		return cadastrar(role.get());		
	}
	
	@GetMapping("/excluirRole/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Role> role = roleRepo.findById(id);
		roleRepo.delete(role.get());
		
		return new ModelAndView("redirect:/listarRole");
	}
}
