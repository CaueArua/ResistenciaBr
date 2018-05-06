package br.com.arua.resistenciaBr.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.repackaged.com.google.common.base.Strings;

import br.com.arua.resistenciaBr.enums.Side;
import br.com.arua.resistenciaBr.model.UnitsModel;
import br.com.arua.resistenciaBr.service.UnitsService;

@Controller
@RequestMapping({"/units"})
public class UnitsController {

	@Autowired
	private UnitsService service;
	
	@RequestMapping({"","/"})
	public ModelAndView home() {
		return  new ModelAndView("units/list");
	}
		
	@GetMapping("/getNewChars")
	@ResponseBody
	public List<UnitsModel> getNewChars() throws ParseException, IOException{
		return service.getNewUnits();
	}
	
	
	@GetMapping("/getNewShips")
	@ResponseBody
	public List<UnitsModel> getNewShips() throws ParseException, IOException{
		return new ArrayList<>();
	}
	
	
	@RequestMapping("/register/{baseId}")
	public ModelAndView register(
				@PathVariable(value="baseId",  required = false) String baseId
			, 	RedirectAttributes redirectAttributes) {
		
		UnitsModel unit = service.getNewUnit(baseId);
		
		if(unit == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Personagem não encontrado");
			return new ModelAndView("redirect:/units");
		}
		
		unit.setNamePtBr(unit.getName());
		unit.setSide(Side.LIGHT_SIDE);
		
		return new ModelAndView("units/register")
				.addObject("unit", unit);
	}
	
	
	
	@PostMapping("/save")
	public ModelAndView save(
				@ModelAttribute("UnitsModel") UnitsModel unit
			, 	RedirectAttributes redirectAttributes) {
		
		service.save(unit);		
		
		return new ModelAndView("redirect:/units");
	}
	
}
