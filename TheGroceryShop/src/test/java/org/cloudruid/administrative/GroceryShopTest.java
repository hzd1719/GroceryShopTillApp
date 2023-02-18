package org.cloudruid.administrative;

import junit.framework.TestCase;

public class GroceryShopTest extends TestCase {

    public void testFinalPrice() {
        GroceryShop groceryShop = new GroceryShop();
        groceryShop.addAGroceryTill(new GroceryShopTill(1));
        String actual = groceryShop.finalPrice(new GroceryShopTill(1));
        String expected = "1 aws and 99 clouds";
        assertEquals(expected, actual);
    }

    public void testSellGroceries() {
    }
}