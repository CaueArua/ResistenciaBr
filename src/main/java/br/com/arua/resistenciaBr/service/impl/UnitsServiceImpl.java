package br.com.arua.resistenciaBr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arua.resistenciaBr.Repository.UnitsRepository;
import br.com.arua.resistenciaBr.model.UnitsModel;
import br.com.arua.resistenciaBr.service.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {

	private static final Map<String,UnitsModel> newUnits;
	
	@Autowired
	private UnitsRepository repository;
	
	static {
		newUnits = new HashMap<>();
	}
	
	@Override
	public List<UnitsModel> getNewUnits(){
		return newUnits.values()
			.stream()
			.collect(Collectors.toList());	
	}
	
	@Override
	public List<UnitsModel> getUnits(){
		return repository.findAll();
	}
	
	
	
}
