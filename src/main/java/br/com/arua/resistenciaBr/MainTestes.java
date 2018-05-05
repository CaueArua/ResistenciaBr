package br.com.arua.resistenciaBr;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import br.com.arua.resistenciaBr.model.UnitsModel;

public class MainTestes {
	public static void main(String[] args) {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet("https://swgoh.gg/api/characters/");
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            
            System.out.println(json);
            
            JsonParser parser = new JsonParser();
    		JsonArray o = parser.parse(json).getAsJsonArray();
    		o.forEach(character -> {
    			UnitsModel collectionModel = new UnitsModel(character.getAsJsonObject());
    			System.out.println(collectionModel);
    		});
    		
            
		}catch (IOException ex) {
        }
	}
}
