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

import br.com.siva.domains.Vaga;
import br.com.siva.repositories.CidadeRepositorio;
import br.com.siva.repositories.EmpresaRepositorio;
import br.com.siva.repositories.VagaRepositorio;


@Controller
public class VagaController {
	
	@Autowired
	private VagaRepositorio vagaRepo;
	@Autowired
	private CidadeRepositorio cidadeRepo;
	@Autowired
	private EmpresaRepositorio empresaRepo;
	
	@GetMapping("/listarVaga")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("vagas");
		mv.addObject("vagas", vagaRepo.findAll());
		
		return mv;
	}
	
	
	@GetMapping("/cadastrarVaga")
	public ModelAndView cadastrar(Vaga vaga) {
		ModelAndView mv = new ModelAndView("vaga");
		mv.addObject("vaga", vaga);
		mv.addObject("empresas", empresaRepo.findAll());
		mv.addObject("cidades", cidadeRepo.findAll());
		
		return mv;
	}
	
	@PostMapping("/salvarVaga")
	public ModelAndView salvar(@Valid Vaga vaga, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(vaga);
		}
		
		vagaRepo.saveAndFlush(vaga);
		
		return new ModelAndView("redirect:/listarVaga");
	}
	
	@GetMapping("/editarVaga/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Vaga> vaga = vagaRepo.findById(id);
		
		return cadastrar(vaga.get());		
	}
	
	@GetMapping("/excluirVaga/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Vaga> vaga = vagaRepo.findById(id);
		vagaRepo.delete(vaga.get());
		
		return new ModelAndView("redirect:/listarVaga");	
	}
}
