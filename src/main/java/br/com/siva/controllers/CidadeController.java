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

import br.com.siva.domains.Cidade;
import br.com.siva.repositories.CidadeRepositorio;
import br.com.siva.repositories.EstadoRepositorio;


@Controller
public class CidadeController {
	
	@Autowired
	private CidadeRepositorio cidadeRepo;
	@Autowired
	private EstadoRepositorio estadoRepo;
	
	@GetMapping("/listarCidade")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("cidades");
		mv.addObject("cidades", cidadeRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarCidade")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("cidade");
		mv.addObject("cidade", cidade);
		mv.addObject("estados", estadoRepo.findAll());
		
		return mv;
	}
	
	@PostMapping("/salvarCidade")
	public ModelAndView salvar(@Valid Cidade cidade, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(cidade);
		}
		
		cidadeRepo.saveAndFlush(cidade);
		
		return new ModelAndView("redirect:/listarCidade");
	}
	
	@GetMapping("/editarCidade/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepo.findById(id);
		
		return cadastrar(cidade.get());		
	}
	
	@GetMapping("/excluirCidade/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepo.findById(id);
		cidadeRepo.delete(cidade.get());
		
		return new ModelAndView("redirect:/listarCidade");	
	}
}
