package org.cloudruid;

import org.cloudruid.administrative.users.Admin;
import org.cloudruid.administrative.Groceries;
import org.cloudruid.administrative.GroceryShop;
import org.cloudruid.administrative.GroceryShopTill;
import org.cloudruid.customers.Customer;
import org.cloudruid.ui.UI;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

       GroceryShop groceryShop = new GroceryShop();
       groceryShop.addAGroceryTill(new GroceryShopTill(1));
       groceryShop.addAGroceryTill(new GroceryShopTill(2));
       groceryShop.addAGroceryTill(new GroceryShopTill(3));
       Admin admin = new Admin(groceryShop);
       UI ui = new UI();

       int n = ui.numberOfGroceriesToAddToTheShop();
       ui.addGrocery(n, admin);
       ui.setSpecialDealItem(admin);
       ui.setTwoForThreeDealItems(admin);

       Customer customer = new Customer();
       customer.addGroceriesToBuy(new Groceries("apple"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("banana"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("banana"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("potato"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("tomato"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("banana"), groceryShop);
       customer.addGroceriesToBuy(new Groceries("potato"), groceryShop);

       List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
       int numberOfTheTill = customer.chooseATill(1);

       System.out.println(groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer));


    }
}
