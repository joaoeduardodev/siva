package br.com.siva.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NegadoController {
	
	@GetMapping("/negarAcesso")
	public ModelAndView negar() {		
		ModelAndView mv = new ModelAndView("negado.html");
		return mv;
	}	
}
