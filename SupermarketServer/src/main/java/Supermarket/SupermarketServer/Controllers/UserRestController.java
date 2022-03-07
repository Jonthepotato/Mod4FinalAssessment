package Supermarket.SupermarketServer.Controllers;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Supermarket.SupermarketServer.Models.RecipeDetails;
import Supermarket.SupermarketServer.Models.UserDetails;
import Supermarket.SupermarketServer.Repositories.SuperMarketRepository;
import Supermarket.SupermarketServer.Service.RecipeService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@RestController
@RequestMapping(path="/api/user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    @Autowired
    private SuperMarketRepository superMarketRepo;

    @Autowired
    private RecipeService recipeSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>saveUserDetails(@RequestBody String data){
        JsonReader jsonReader = Json.createReader(new StringReader(data));
        JsonArray jsonArray = jsonReader.readArray().asJsonArray();
        for (JsonValue jsonValue: jsonArray) {
            JsonObject jsonObj = jsonValue.asJsonObject();
            UserDetails userDetails = new UserDetails();
            // jsonObj.getString("name");
            // jsonObj.getString("email");
            // jsonObj.getString("itemName");
            userDetails.setName(jsonObj.getString("name"));
            userDetails.setEmail(jsonObj.getString("email"));
            userDetails.setItemName(jsonObj.getString("itemName"));
            userDetails.setItemPrice(jsonObj.getString("itemPrice"));
            userDetails.setItemUnit(jsonObj.getString("itemUnit"));
            userDetails.setItemNameUnit(userDetails.getItemName()+" "+userDetails.getItemUnit());
            System.out.println(userDetails.getName());
            System.out.println(userDetails.getEmail());
            System.out.println(userDetails.getItemName());
            superMarketRepo.addUserDetails(userDetails);
        }
        JsonObjectBuilder payload = Json.createObjectBuilder();
        payload.add("message", "User saved");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(payload.build().toString());
    }

    
    
}
