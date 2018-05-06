package br.com.arua.resistenciaBr.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@RequestMapping({"/","/index"})
	public ModelAndView home(Map<String, Object> model) {
		
		return  new ModelAndView("welcome");
		
	}
	
}
