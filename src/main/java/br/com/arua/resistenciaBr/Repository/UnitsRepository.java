package br.com.arua.resistenciaBr.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.arua.resistenciaBr.model.UnitsModel;

public interface UnitsRepository extends MongoRepository<UnitsModel, String> {

}
