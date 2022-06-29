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
import br.com.siva.domains.Estado;
import br.com.siva.repositories.CidadeRepositorio;
import br.com.siva.repositories.EmpresaRepositorio;
import br.com.siva.repositories.EstadoRepositorio;
import br.com.siva.repositories.VagaRepositorio;


@Controller
public class CidadeController {
	
	@Autowired
	private CidadeRepositorio cidadeRepo;
	@Autowired
	private EstadoRepositorio estadoRepo;
	@Autowired
	private VagaRepositorio vagaRepo;
	@Autowired
	private EmpresaRepositorio empresaRepo;
	
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
		ModelAndView mv = new ModelAndView("redirect:/listarCidade");
		if(empresaRepo.findByCidade(id) > 0 && vagaRepo.findByCidade(id) > 0) {
			mv.addObject("messageType", "error");
			mv.addObject("message", "Exitem dados que fazem o uso desse cidade.");
			return mv;
		}
		cidadeRepo.delete(cidade.get());
		mv.addObject("messageType", "success");
		mv.addObject("message", "Cidade removido com sucesso.");
		return mv;
	}
}
