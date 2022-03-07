package Supermarket.SupermarketServer.Repositories;

public interface SQL {
    //For promo list
    public static final String SQL_GET_ALL_PROMO_ITEMS="select * from promo_items";
    public static final String SQL_UPDATE_PROMO_ITEMS="insert into promo_items (item_name,item_price,item_unit) values (?,?,?)";
    public static final String SQL_MAX_TIME="select max(date) from promo_items";
    public static final String SQL_DELETE_PROMO_ITEMS="delete from promo_items where item_id != '9000000'";
    public static final String SQL_COUNT_OF_PROMO_ITEMS="select count(item_name) from promo_items";

    //For userlist
    public static final String SQL_ADD_USER_LIST="insert into user_list (name,email,item_name,item_price,item_unit,item_name_unit) values (?,?,?,?,?,?)";
    public static final String SQL_GET_ALL_USER_LIST="select * from user_list";
    //For itemlist
    public static final String SQL_ADD_SEARCH_ITEM_LIST="insert into search_item (item_type,item_name,item_name_unit) values (?,?,?)";
}
