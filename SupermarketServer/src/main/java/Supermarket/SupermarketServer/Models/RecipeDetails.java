package Supermarket.SupermarketServer.Models;

import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;

public class RecipeDetails {
    String title;
    List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public static JsonObject setDetails(JsonObject data){
        
        RecipeDetails recipeDetails = new RecipeDetails();
        Ingredient ingredient = new Ingredient();
        List<Ingredient> ingredients = new LinkedList<Ingredient>();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        recipeDetails.setTitle(data.getJsonArray("recipes").get(0).asJsonObject().getString("title"));
        JsonArray jsonArray = data.getJsonArray("recipes").get(0).asJsonObject().getJsonArray("extendedIngredients");
        for (JsonValue jsonValue: jsonArray) {
            JsonObject jsonObj = jsonValue.asJsonObject();
            ingredients.add(new Ingredient(jsonObj.getString("nameClean")));
            jsonArrayBuilder.add(jsonObj.getString("nameClean"));
            // System.out.println(ingredients);
    }

    recipeDetails.setIngredients(ingredients);
    JsonObjectBuilder recipeObj = Json.createObjectBuilder();
    JsonObject resp = Json.createObjectBuilder()
        .add("title", recipeDetails.getTitle())
        .add("ingredients", jsonArrayBuilder)
        .build();
    
    return resp;
}


//     public JsonObject jsonObjectBuilder(){
//         JsonObject resp = Json.createObjectBuilder()
//         .add("title", title)
//         .add("ingredients", ingredients)
//         .build();
//         return resp;
// }
}
