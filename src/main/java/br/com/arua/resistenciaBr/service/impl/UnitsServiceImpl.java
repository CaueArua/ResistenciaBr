package br.com.arua.resistenciaBr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.com.arua.resistenciaBr.Repository.UnitsRepository;
import br.com.arua.resistenciaBr.model.UnitsModel;
import br.com.arua.resistenciaBr.service.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {

	private static final Map<String,UnitsModel> newUnits;
	private static final Map<String,UnitsModel> okUnits;
	
	@Autowired
	private UnitsRepository repository;
	
	static {
		newUnits = Collections.synchronizedMap(new HashMap<>());
		okUnits  = Collections.synchronizedMap(new HashMap<>());
	}
	
	@Override
	public List<UnitsModel> getNewUnits(){
		checkNewUnits();
		
		return newUnits.values()
			.stream()
			.collect(Collectors.toList());	
	}
	
	@Override
	public List<UnitsModel> getUnits(){
		return getOkUnits().values()
				.stream()
				.collect(Collectors.toList());	
	}

	@Override
	public UnitsModel getNewUnit(String baseId) {
		
		if(getOkUnits().containsKey(baseId)) {
			newUnits.remove(baseId);
			return null;
		}
		
		return newUnits.get(baseId);
	}
		
	private Map<String,UnitsModel> getOkUnits(){
		if(okUnits.isEmpty()) {
			List<UnitsModel> units = repository.findAll();
			units.forEach(u -> okUnits.put(u.getBaseId(), u));
		}
		return okUnits;
	}
	
	
	private void checkNewUnits(){		
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
    			if(getOkUnits().containsKey(unit.getBaseId())) {
    				return;
    			}    			
    			
    			newUnits.putIfAbsent(unit.getBaseId(), unit);
    		});
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void save(UnitsModel unit) {
		if(newUnits.containsKey(unit.getBaseId())) {
			newUnits.remove(unit.getBaseId());
			okUnits.put(unit.getBaseId(), unit);
			repository.save(unit);
		}
	}
	
	
	
}
