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

import br.com.siva.domains.Estado;
import br.com.siva.repositories.EstadoRepositorio;


@Controller
public class EstadoController {
	
	@Autowired
	private EstadoRepositorio estadoRepo;
	
	@GetMapping("/listarEstado")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("estados");
		mv.addObject("estados", estadoRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarEstado")
	public ModelAndView cadastrar(Estado estado) {
		ModelAndView mv = new ModelAndView("estado");
		mv.addObject("estado", estado);
		
		return mv;
	}
	
	@PostMapping("/salvarEstado")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(estado);
		}
		
		estadoRepo.saveAndFlush(estado);

		return new ModelAndView("redirect:/listarEstado");
	}
	
	@GetMapping("/editarEstado/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepo.findById(id);
		
		return cadastrar(estado.get());		
	}
	
	@GetMapping("/excluirEstado/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepo.findById(id);
		estadoRepo.delete(estado.get());
		
		return new ModelAndView("redirect:/listarEstado");
	}
}
