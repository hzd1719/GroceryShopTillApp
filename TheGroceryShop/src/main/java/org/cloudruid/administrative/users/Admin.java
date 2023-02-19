package org.cloudruid.administrative.users;

import org.cloudruid.administrative.Groceries;
import org.cloudruid.administrative.GroceryShop;

import java.math.BigDecimal;

public class Admin {

    private GroceryShop groceryShop;

    public Admin(GroceryShop groceryShop) {
        this.groceryShop = groceryShop;
    }

    public boolean addGroceryToTheShop(Groceries grocery, BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0 || price.scale() > 2) {
            throw new IllegalArgumentException("Invalid price!");
        }
        if(grocery == null) {
            throw new IllegalArgumentException("Invalid grocery!");
        }
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
