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

import br.com.siva.domains.Empresa;
import br.com.siva.repositories.CidadeRepositorio;
import br.com.siva.repositories.EmpresaRepositorio;


@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaRepositorio empresaRepo;
	@Autowired
	private CidadeRepositorio cidadeRepo;
	
	@GetMapping("/listarEmpresa")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("empresas");
		mv.addObject("empresas", empresaRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarEmpresa")
	public ModelAndView cadastrar(Empresa empresa) {
		ModelAndView mv = new ModelAndView("empresa");
		mv.addObject("empresa", empresa);
		mv.addObject("cidades", cidadeRepo.findAll());
		
		return mv;
	}
	
	@PostMapping("/salvarEmpresa")
	public ModelAndView salvar(@Valid Empresa empresa, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(empresa);
		}
		
		empresaRepo.saveAndFlush(empresa);
		
		return new ModelAndView("redirect:/listarEmpresa");
	}
	
	@GetMapping("/editarEmpresa/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Empresa> empresa = empresaRepo.findById(id);
		
		return cadastrar(empresa.get());		
	}
	
	@GetMapping("/excluirEmpresa/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Empresa> empresa = empresaRepo.findById(id);
		empresaRepo.delete(empresa.get());
		
		return new ModelAndView("redirect:/listarEmpresa");	
	}
}
