package Supermarket.SupermarketServer.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Supermarket.SupermarketServer.Models.RecipeDetails;
import Supermarket.SupermarketServer.Repositories.SuperMarketRepository;
import Supermarket.SupermarketServer.Service.RecipeService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path="/api/recipe", produces =MediaType.APPLICATION_JSON_VALUE)
public class RecipeRestController {

    @Autowired
    private SuperMarketRepository superMarketRepo;

    @Autowired
    private RecipeService recipeSvc;

    @GetMapping
    public ResponseEntity<String> getRecipeDetails() throws IOException{
        JsonObject resp = recipeSvc.getData(recipeSvc.getRecipeDetails());
        // RecipeDetails recipeDetails= recipeSvc.getData(recipeSvc.getRecipeDetails(recipeCategory));
        // JsonObjectBuilder recipeObj = Json.createObjectBuilder();
        // JsonArrayBuilder recipeArray=Json.createArrayBuilder();
        // for (int i=0;i<recipeDetailsList.size();i++){
        //     RecipeDetails recipeDetails =recipeDetailsList.get(i);
        //     JsonObject jo = Json.createObjectBuilder()
        //     .add("title",recipeDetails.getTitle())
        //     .add("category",recipeDetails.getCategory())
        //     .add("img",recipeDetails.getImg())
        //     .add("source",recipeDetails.getSource())
        //     .add("url",recipeDetails.getUrl())
        //     .build();
        //     recipeArray.add(jo);
        // }
        return ResponseEntity.ok(resp.toString());

    }
}
