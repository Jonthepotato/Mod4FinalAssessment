package Supermarket.SupermarketServer.Models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Item{
    private String title;
    private String price;
    private String unit;
    private String itemNameUnit;

    public String getItemNameUnit() {
        return this.itemNameUnit;
    }

    public void setItemNameUnit(String itemNameUnit) {
        this.itemNameUnit = itemNameUnit;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static Item populateItem(SqlRowSet rs){
        final Item item= new Item();
        item.setTitle(rs.getString("item_name"));
        item.setPrice(rs.getString("item_price"));
        item.setUnit(rs.getString("item_unit"));
        return item;
        
    }

    public static Item populatePromoItem(SqlRowSet rs){
        final Item item = new Item();
        item.setTitle(rs.getString("item_name"));
        item.setPrice(rs.getString("item_price"));
        item.setUnit(rs.getString("item_unit"));
        item.setItemNameUnit(item.getTitle()+" "+item.getUnit());
        System.out.println(item);
        return item;
    }

    
    
}
