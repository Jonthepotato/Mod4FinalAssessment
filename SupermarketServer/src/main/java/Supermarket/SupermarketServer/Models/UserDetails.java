package Supermarket.SupermarketServer.Models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UserDetails {

    String name;
    String email;
    String itemName;
    String itemPrice;
    String itemUnit;
    String itemNameUnit;

    public String getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemUnit() {
        return this.itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getItemNameUnit() {
        return this.itemNameUnit;
    }

    public void setItemNameUnit(String itemNameUnit) {
        this.itemNameUnit = itemNameUnit;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static UserDetails populateUserItem(SqlRowSet rs){
        final UserDetails userDetails = new UserDetails();
        userDetails.setItemName(rs.getString("item_name"));
        userDetails.setItemPrice(rs.getString("item_price"));
        userDetails.setItemUnit(rs.getString("item_unit"));
        userDetails.setItemNameUnit(rs.getString("item_name_unit"));
        return userDetails;

    }
    
}
