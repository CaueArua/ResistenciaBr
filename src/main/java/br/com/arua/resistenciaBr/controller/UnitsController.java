package br.com.arua.resistenciaBr.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.com.arua.resistenciaBr.Repository.UnitsRepository;
import br.com.arua.resistenciaBr.model.UnitsModel;

@Controller
@RequestMapping({"/units"})
public class UnitsController {

	@Autowired
	private UnitsRepository repository;
	
	@RequestMapping({"","/"})
	public ModelAndView home() {
		return  new ModelAndView("units/list");
	}
		
	@GetMapping("/getNew")
	@ResponseBody
	public List<UnitsModel> getNew() throws ParseException, IOException{
		
		Map<String, UnitsModel> newUnits = new HashMap<>();
		Map<String, UnitsModel> okUnits = new HashMap<>();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet("https://swgoh.gg/api/characters/");
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            System.out.println(json);
            
            JsonParser parser = new JsonParser();
    		JsonArray o = parser.parse(json).getAsJsonArray();
    		o.forEach(character -> {
    			UnitsModel unit = new UnitsModel(character.getAsJsonObject());
    			newUnits.put(unit.getBaseId(), unit);
    		});
		}
		
		
		List<UnitsModel> units = repository.findAll();
		units.forEach(u -> okUnits.put(u.getBaseId(), u));
		
		
		List<UnitsModel> ret = new ArrayList<>();
		
		newUnits.forEach((key,unit) -> {
			if(!okUnits.containsKey(key)) {
				ret.add(unit);
			}
		});
		
		return ret;
	}
	
	
	
	
}
