package Supermarket.SupermarketServer.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import Supermarket.SupermarketServer.Models.RecipeDetails;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class RecipeService {

    public String getRecipeDetails(){
        
        // String recipeInput = recipeCategory.trim().replaceAll(" ","%20");
        // System.out.println("String>>>>>>>" + recipeInput);
        String url= UriComponentsBuilder
        .fromUriString("https://api.spoonacular.com/recipes/random")
        .queryParam("apiKey", System.getenv("apiKey"))
        .toUriString();
        System.out.println("uri string>>>>>>>>>>>>>"+"https://api.spoonacular.com/recipes/random");

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        String respBody = resp.getBody();
        System.out.println("Respbody>>>>>>>" +respBody);
           return respBody;

    }

    public JsonObject getData(String respBody) throws IOException{
        RecipeDetails recipeDetailsList = new RecipeDetails();
        try(InputStream is = new ByteArrayInputStream(respBody.getBytes())){
            JsonReader reader= Json.createReader(is);
            JsonObject data = reader.readObject();
            JsonObject resp = RecipeDetails.setDetails(data);
            // JsonArray data = reader.readArray().asJsonArray();
            // for(JsonValue jsonValue: data){
            //     JsonObject jsonObj = jsonValue.asJsonObject();
            //     recipeDetailsList = new LinkedList<RecipeDetails>();
            //     RecipeDetails recipeDetails = new RecipeDetails();
            //     recipeDetails.setTitle(jsonObj.getString("title"));
            //     recipeDetails.setCategory(jsonObj.getString("category"));
            //     recipeDetails.setImg(jsonObj.getString("img"));
            //     recipeDetails.setUrl(jsonObj.getString("url"));
            //     recipeDetails.setSource(jsonObj.getString("source"));
            //     recipeDetailsList.add(recipeDetails);
            //     System.out.println("Recipe Details>>>>" +recipeDetails);
            // }
            System.out.println("Recipe Details List>>>>"+recipeDetailsList);
            
            // RecipeDetails recipeDetails=RecipeDetails.setDetails(data);
            return resp;
        }
    }
    
}
