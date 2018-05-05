package br.com.arua.resistenciaBr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.JsonObject;

import br.com.arua.resistenciaBr.enums.CombatType;
import br.com.arua.resistenciaBr.enums.Side;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@Document(collection = "colecao2")
public class UnitsModel {
	
	@Id
	private String baseId;
	private Integer pk;
	private String name;
	private Integer maxPower;
	private String url;
	private String image;
	private String description;
	private CombatType combatType;
	private Side side;
	
	private String namePtBr;
	private String descriptionPtBr;	

	public UnitsModel() {
		
	}
	
	public UnitsModel(JsonObject json) {
		name = json.get("name").getAsString();
		baseId = json.get("base_id").getAsString();
		pk = json.get("pk").getAsInt();
		url = json.get("url").getAsString();
		image = json.get("image").getAsString();
		maxPower = json.get("power").getAsInt();
		description = json.get("description").getAsString();
		
		combatType = json.get("combat_type").getAsInt() == 1 ? CombatType.CHARACTER : CombatType.SHIP;
	}
	
	



}
