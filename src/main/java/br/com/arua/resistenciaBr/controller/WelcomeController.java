package br.com.arua.resistenciaBr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.arua.resistenciaBr.Repository.UnitsRepository;
import br.com.arua.resistenciaBr.enums.CombatType;
import br.com.arua.resistenciaBr.enums.Side;
import br.com.arua.resistenciaBr.model.UnitsModel;

@Controller
public class WelcomeController {

	@Autowired
	private UnitsRepository repository;
	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping({"/","/index"})
	public ModelAndView home(Map<String, Object> model) {
		
		
		Gson gson = new Gson();
		JsonObject asJsonObject = gson.toJsonTree(" {\r\n" + 
				"    \"name\": \"Ahsoka Tano's Jedi Starfighter\",\r\n" + 
				"    \"base_id\": \"JEDISTARFIGHTERAHSOKATANO\",\r\n" + 
				"    \"url\": \"https://swgoh.gg/ships/ahsoka-tanos-jedi-starfighter/\",\r\n" + 
				"    \"image\": \"//swgoh.gg/static/img/assets/tex.charui_jedi_fighter_ahsoka.png\",\r\n" + 
				"    \"power\": 42771,\r\n" + 
				"    \"description\": \"Versatile Jedi Attacker that uses dispels and buffs to adapt to the battle\",\r\n" + 
				"    \"combat_type\": 2\r\n" + 
				"  }").getAsJsonObject();
		
		System.out.println(asJsonObject);
		
		model.put("message", this.message);
		return  new ModelAndView("welcome");
		
	}
	
}
