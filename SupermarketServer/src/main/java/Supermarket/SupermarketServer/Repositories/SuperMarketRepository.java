package Supermarket.SupermarketServer.Repositories;

import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import Supermarket.SupermarketServer.Models.Item;
import Supermarket.SupermarketServer.Models.UserDetails;

@Repository
public class SuperMarketRepository implements SQL{

    @Autowired
    private JdbcTemplate template;

    public List<Item> getAllItems(){
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_PROMO_ITEMS);
        final List<Item> itemList = new LinkedList<Item>();
        while (rs.next()){
            final Item item = Item.populateItem(rs);
            itemList.add(item);
        }
        return itemList;
    }

    public boolean updatePromoItems(final Item updateItem){
        int added = template.update(SQL_UPDATE_PROMO_ITEMS
        ,updateItem.getTitle()
        ,updateItem.getPrice()
        ,updateItem.getUnit());
        return added>0;
    }

    public Long getLatestItemTime(){
        final SqlRowSet rs = template.queryForRowSet(SQL_MAX_TIME);
        Long timeStamp=0L;
        while (rs.next()){
            System.out.println("TIMESTAMP>>>>>>>>>>>>>>>>>>>>" +timeStamp);
            timeStamp = rs.getTimestamp(1).getTime();
            // timeStamp = new Long(rs.getTimestamp(1).getTime());
            System.out.println("TIMESTAMP1>>>>>>>>>>>>>>>>>>>>" +timeStamp);
            if (timeStamp==0L){
                return timeStamp =1L;
            }
        }
        return timeStamp;
    }

    public void deletePromoItems(){
        template.execute(SQL_DELETE_PROMO_ITEMS);
        // conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "Manish", "123456");
        // PreparedStatement preparedStatement = SQL_DELETE_PROMO_ITEMS
        // final SqlRowSet rs = template.update(sql, args);
    }

    public Integer getCountInPromoItems(){
        Integer count = 0;
        final SqlRowSet rs = template.queryForRowSet(SQL_COUNT_OF_PROMO_ITEMS);
        while(rs.next()){
            count = rs.getInt(1);
        }
        return count;
    }

    public boolean addUserDetails(final UserDetails updateUserDetails){
        int added = template.update(SQL_ADD_USER_LIST
        ,updateUserDetails.getName()
        ,updateUserDetails.getEmail()
        ,updateUserDetails.getItemName()
        ,updateUserDetails.getItemPrice()
        ,updateUserDetails.getItemUnit()
        ,updateUserDetails.getItemNameUnit());
        return added>0;

    }

    public boolean addSearchDetails(final String updateSearchItem, String searchedItemTitle, String searchedItemTitleUnit){
        int added=template.update(SQL_ADD_SEARCH_ITEM_LIST
        ,updateSearchItem
        ,searchedItemTitle
        ,searchedItemTitleUnit);
        return added>0;
    }

     
    public List<Item> getUserItems(){
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_USER_LIST);
        final List<Item> userItemList = new LinkedList<Item>();
        while (rs.next()){
            final Item userItem = Item.populatePromoItem(rs);
            userItemList.add(userItem);
        }
        return userItemList;
    }

    public List<Item> getPromoItems(){
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_PROMO_ITEMS);
        final List<Item> itemList = new LinkedList<Item>();
        while (rs.next()){
            final Item item = Item.populatePromoItem(rs);
            itemList.add(item);
        }
        return itemList;
    }
    
}
