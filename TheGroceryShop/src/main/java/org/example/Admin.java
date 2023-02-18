package org.example;

import java.math.BigDecimal;

public class Admin {

    private GroceryShop groceryShop;

    public Admin(GroceryShop groceryShop) {
        this.groceryShop = groceryShop;
    }

    public boolean addGroceryToTheShop(Groceries grocery, BigDecimal price) {
        return this.groceryShop.addGrocery(grocery, price);
    }

    public boolean setSpecialDealItemToTheShop(Groceries grocery) {
        return this.groceryShop.setSpecialDeal(grocery);
    }

    public boolean addToTheTwoForThreeDeal(Groceries grocery) {
        return this.groceryShop.addToTwoForThreeDeal(grocery);
    }

    public boolean checkIfGroceryIsAvailable(Groceries grocery) {
        return groceryShop.getAvailableGroceries().containsKey(grocery);
    }

    public GroceryShop getGroceryShop() {
        return groceryShop;
    }

    public int getCapacityForTwoForThreeOfTheShop() {
        return GroceryShop.getCapacity();
    }
}
