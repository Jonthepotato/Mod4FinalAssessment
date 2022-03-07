package Supermarket.SupermarketServer.Service;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.springframework.stereotype.Service;

import Supermarket.SupermarketServer.Models.Item;

@Service
public class SuperMarketService {

    public List<Item> scrapePromoData(){
        List<Item> items = new LinkedList<>();
    try{
        // String searchQuery = "milk";
        String baseUrl = "https://www.fairprice.com.sg/promotions";
              final WebClient client = new WebClient();
              client.getOptions().setCssEnabled(false);
              client.getOptions().setJavaScriptEnabled(false);
              final HtmlPage page = client.getPage(baseUrl);
              // System.out.println(page.asNormalizedText());
              // System.out.println(page.asXml());
              // System.out.println(baseUrl+"search?query="+URLEncoder.encode(searchQuery, "UTF-8"));
              List<HtmlElement> itemElements = page.getByXPath(".//div[@class='sc-1plwklf-8 jQmMcy']");
              if(itemElements.isEmpty()){
                System.out.println("No items found");
              }
              else{
                for(HtmlElement htmlItem : itemElements){
                  HtmlElement itemPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-11 bxPmui']/div[@class='sc-1plwklf-10 jNMebL']/span[@class='sc-1bsd7ul-1 hiLGVO']/span[@class='sc-1bsd7ul-1 gJhHzP']"));
                  HtmlElement itemTitle = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-7 jibnUN']/span[@class='sc-1bsd7ul-1 gGWxuk']"));
                  HtmlElement itemUnit = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-7 jibnUN']/div[@class='sc-1plwklf-2 sc-bo8pbc-0 eXNivM kVLFRR']/div[@class='sc-1plwklf-14 iBVGUf']/span[@class='sc-1bsd7ul-1 LLmwF']"));
                  // System.out.println(itemPrice.asNormalizedText());
                  // System.out.println(itemTitle.asNormalizedText());
                  // System.out.println(itemUnit.asNormalizedText());
                  Item item = new Item();
                  item.setTitle(itemTitle.asNormalizedText());
                  item.setPrice(itemPrice.asNormalizedText());
                  item.setUnit(itemUnit.asNormalizedText());
                  items.add(item);
                }
              }
              
          }catch(Exception e) {
              e.printStackTrace();
          }
        return items;
        }

        public List<Item> scrapeQueryData(String searchQuery){
                List<Item> items = new LinkedList<>();
            try{
                // String searchQuery = "milk";
                String baseUrl = "https://www.fairprice.com.sg/";
                      final WebClient client = new WebClient();
                      client.getOptions().setCssEnabled(false);
                      client.getOptions().setJavaScriptEnabled(false);
                      final HtmlPage page = client.getPage(baseUrl+"search?query="+URLEncoder.encode(searchQuery, "UTF-8"));
                      // System.out.println(page.asNormalizedText());
                      // System.out.println(page.asXml());
                      // System.out.println(baseUrl+"search?query="+URLEncoder.encode(searchQuery, "UTF-8"));
                      List<HtmlElement> itemElements = page.getByXPath(".//div[@class='sc-1plwklf-8 jQmMcy']");
                      if(itemElements.isEmpty()){
                        System.out.println("No items found");
                      }
                      else{
                        for(HtmlElement htmlItem : itemElements){
                          HtmlElement itemPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-11 bxPmui']/div[@class='sc-1plwklf-10 jNMebL']/span[@class='sc-1bsd7ul-1 hiLGVO']/span[@class='sc-1bsd7ul-1 gJhHzP']"));
                          HtmlElement itemTitle = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-7 jibnUN']/span[@class='sc-1bsd7ul-1 gGWxuk']"));
                          HtmlElement itemUnit = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='sc-1plwklf-7 jibnUN']/div[@class='sc-1plwklf-2 sc-bo8pbc-0 eXNivM kVLFRR']/div[@class='sc-1plwklf-14 iBVGUf']/span[@class='sc-1bsd7ul-1 LLmwF']"));
                          // System.out.println(itemPrice.asNormalizedText());
                          // System.out.println(itemTitle.asNormalizedText());
                          // System.out.println(itemUnit.asNormalizedText());
                          Item item = new Item();
                          item.setTitle(itemTitle.asNormalizedText());
                          item.setPrice(itemPrice.asNormalizedText());
                          item.setUnit(itemUnit.asNormalizedText());
                          items.add(item);
                        }
                      }
                      
                  }catch(Exception e) {
                      e.printStackTrace();
                  }
                return items;
                }
      }
