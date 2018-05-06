package br.com.arua.resistenciaBr.service;

import java.util.List;

import br.com.arua.resistenciaBr.model.UnitsModel;

public interface UnitsService {

	public List<UnitsModel> getNewUnits();
	public List<UnitsModel> getUnits();
	public UnitsModel getNewUnit(String baseId);
	public void save(UnitsModel unit);

}
