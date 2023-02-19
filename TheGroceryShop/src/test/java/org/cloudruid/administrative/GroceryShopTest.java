package org.cloudruid.administrative;

import junit.framework.TestCase;
import org.cloudruid.administrative.users.Admin;
import org.cloudruid.customers.Customer;

import java.math.BigDecimal;
import java.util.List;

public class GroceryShopTest extends TestCase {

    public void testSellGroceries() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.5));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.4));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.3));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.26));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(banana);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(apple, groceryShop);
        customer.addGroceriesToBuy(banana, groceryShop);
        customer.addGroceriesToBuy(banana, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(tomato, groceryShop);
        customer.addGroceriesToBuy(banana, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);

        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "1 aws and 99 clouds";
        assertEquals(expected, actual);
    }

    public void testSellGroceriesSpecial() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.5));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.4));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.3));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.26));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(banana);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);

        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "0 aws and 39 clouds";
        assertEquals(expected, actual);
    }

    public void testSellGroceriesOneItem() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.5));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.4));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.3));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.26));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(apple, groceryShop);
        customer.addGroceriesToBuy(tomato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);

        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "1 aws and 32 clouds";
        assertEquals(expected, actual);
    }


    public void testSellGroceriesTwoForThree() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.5));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.4));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.3));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.26));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(apple, groceryShop);
        customer.addGroceriesToBuy(tomato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);

        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "0 aws and 80 clouds";
        assertEquals(expected, actual);
    }

    public void testSellGroceriesCheapestPossible() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.01));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(apple, groceryShop);
        customer.addGroceriesToBuy(tomato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);
        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "0 aws and 02 clouds";
        assertEquals(expected, actual);
    }

    public void testSellGroceriesManySpecials() {
        GroceryShop groceryShop = new GroceryShop();
        Admin admin = new Admin(groceryShop);

        Groceries apple = new Groceries("apple");
        Groceries banana = new Groceries("banana");
        Groceries tomato = new Groceries("tomato");
        Groceries potato = new Groceries("potato");

        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        admin.addGroceryToTheShop(apple, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(banana, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(tomato, BigDecimal.valueOf(0.01));
        admin.addGroceryToTheShop(potato, BigDecimal.valueOf(0.01));
        admin.setSpecialDealItemToTheShop(potato);
        admin.addToTheTwoForThreeDeal(potato);
        admin.addToTheTwoForThreeDeal(apple);
        admin.addToTheTwoForThreeDeal(tomato);

        Customer customer = new Customer();
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);
        customer.addGroceriesToBuy(potato, groceryShop);

        List<Groceries> groceriesListCustomer = customer.getGroceriesToBuy();
        int numberOfTheTill = customer.chooseATill(1);
        String actual = groceryShop.sellGroceries(new GroceryShopTill(numberOfTheTill), groceriesListCustomer);
        String expected = "0 aws and 03 clouds";
        assertEquals(expected, actual);
    }

   
}