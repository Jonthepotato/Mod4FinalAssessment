package Supermarket.SupermarketServer.Controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.gargoylesoftware.htmlunit.javascript.host.fetch.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Supermarket.SupermarketServer.Models.Item;
import Supermarket.SupermarketServer.Models.RecipeDetails;
import Supermarket.SupermarketServer.Models.UserDetails;
import Supermarket.SupermarketServer.Repositories.SuperMarketRepository;
import Supermarket.SupermarketServer.Service.RecipeService;
import Supermarket.SupermarketServer.Service.SuperMarketService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path="/api/items", produces =MediaType.APPLICATION_JSON_VALUE)
public class ItemsRestController{

    @Autowired
    private SuperMarketService superMarketSvc;
    
    @Autowired
    private SuperMarketRepository superMarketRepo;

    @Autowired
    private RecipeService recipeSvc;


    @GetMapping(path="/promo")
    public ResponseEntity<String> getPromoItems() {
        List<Item> promoItemList= new LinkedList<Item>();
        // Long latestItemTime = superMarketRepo.getLatestItemTime();
        // long latestItemTime = System.currentTimeMillis();
        // System.out.println("LATEST TIME >>>>>>>>>>>>>>>>>" + latestItemTime);
        int promoItemCount = superMarketRepo.getCountInPromoItems();
        // int promoItemCount =0;
        System.out.println("COUNT OF ITEMS >>>>>>>>>>>>>>>>>" + promoItemCount);
        if (promoItemCount==0){
            promoItemList = superMarketSvc.scrapePromoData();
        } else{
            superMarketRepo.deletePromoItems();
            promoItemList = superMarketSvc.scrapePromoData();
        //     System.out.println("Current time>>>>" +System.currentTimeMillis()/1000000000);
        //     System.out.println("Timestamp from repo>>>>" +System.currentTimeMillis()/1000000000);
        //     Long timeDiff = System.currentTimeMillis()/1000000000 - superMarketRepo.getLatestItemTime()/1000000000;
        //     System.out.println("TimeDiff/1000000>>>>>>>>>>>" +timeDiff);
        //     if (timeDiff>600000000){
        //     superMarketRepo.deletePromoItems();
        //     System.out.println("AFTER DELETE COUNT>>>>>>>>" +promoItemList.size());
        //     promoItemList = superMarketSvc.scrapePromoData();
        //     System.out.println("AFTER DELETE COUNT and add>>>>>>>>" +promoItemList.size());
        // }
        }
        System.out.println("promoitemlistsize>>>>>>>" +promoItemList.size()); 
        for (int i=0; i<promoItemList.size();i++){
            Item newPromoItem = new Item();
            newPromoItem.setTitle(promoItemList.get(i).getTitle());
            newPromoItem.setPrice(promoItemList.get(i).getPrice());
            newPromoItem.setUnit(promoItemList.get(i).getUnit());
            superMarketRepo.updatePromoItems(newPromoItem);
        }

        JsonArrayBuilder itemArray = Json.createArrayBuilder();
        for (int i=0; i<promoItemList.size(); i++){
            Item item = promoItemList.get(i);
            JsonObject jo = Json.createObjectBuilder()
            .add("title",item.getTitle())
            .add("price",item.getPrice())
            .add("unit",item.getUnit())
            .build();
            itemArray.add(jo);
        }
        // System.out.println(promoItemList);
        return ResponseEntity.ok(itemArray.build().toString());
    }

    @GetMapping(path="/search/{searchItem}")
    public ResponseEntity<String> getQueryItems(@PathVariable String searchItem){
        List<Item> queryItemList = superMarketSvc.scrapeQueryData(searchItem);
        JsonArrayBuilder searchedItemArray = Json.createArrayBuilder();
        for (int i =0; i<queryItemList.size();i++){
            Item searchedItem = queryItemList.get(i);
            JsonObject jo = Json.createObjectBuilder()
            .add("title",searchedItem.getTitle())
            .add("price",searchedItem.getPrice())
            .add("unit",searchedItem.getUnit())
            .build();
            searchedItemArray.add(jo);
            //add into item db
            superMarketRepo.addSearchDetails(searchItem,searchedItem.getTitle(),searchedItem.getTitle()+" "+searchedItem.getUnit());
        }
        return ResponseEntity.ok(searchedItemArray.build().toString());
        
    }

    @GetMapping(path="/compare")
    public ResponseEntity<String> compareItems() {
        List<Item> promoItemList = superMarketRepo.getPromoItems();
        List<Item> userItemList = superMarketRepo.getUserItems();
        System.out.println("userItemList>>>>>>" + userItemList);
        System.out.println("promoItemList>>>>>" +promoItemList);

        HashSet<String> hashSet = new HashSet<String>();
        List<Item> overlap = new LinkedList<Item>();

        for(int i=0; i<promoItemList.size();i++){
        hashSet.add(promoItemList.get(i).getItemNameUnit());}
        for(int i=0; i<userItemList.size();i++){
            if (hashSet.contains(userItemList.get(i).getItemNameUnit())) {
                overlap.add(userItemList.get(i));
            }
        }
        // for (int i =0; i<Math.max(promoItemList.size(), userItemList.size()); i++){
        //     if (promoItemList.get(i).getItemNameUnit()==userItemList.get(i).getItemNameUnit()) {
        //         overlap.add(promoItemList.get(i));
        //     }

        JsonArrayBuilder overlapItemArray = Json.createArrayBuilder();
        if(overlap.size()==0){
            return ResponseEntity.ok(overlapItemArray.build().toString());
        }
        for (int i=0; i<overlap.size(); i++){
            Item item = overlap.get(i);
            JsonObject jo = Json.createObjectBuilder()
            .add("title",item.getTitle())
            .add("price",item.getPrice())
            .add("unit",item.getUnit())
            .add("itemNameUnit",item.getItemNameUnit())
            .build();
            overlapItemArray.add(jo);
        }
        System.out.println(overlapItemArray);
        return ResponseEntity.ok(overlapItemArray.build().toString());

    }

    @GetMapping(path="/hello")
    public String getUserList(){
        return "Hello";
    }

    @GetMapping
    public String sayHello(){
        return "Bye";
    }
    }


    



