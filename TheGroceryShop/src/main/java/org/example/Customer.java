package org.example;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    List<Groceries> groceriesToBuy = new ArrayList<>();


    public void addGroceriesToBuy(Groceries grocery, GroceryShop groceryShop) {
        if(groceryShop.getAvailableGroceries().containsKey(grocery)) {
            this.groceriesToBuy.add(grocery);
        }

        else {
            System.out.println("This grocery can't be bought from that shop!");
        }
    }

    public int chooseATill(int n) {
        return n;
    }

    public List<Groceries> getGroceriesToBuy() {
        return groceriesToBuy;
    }
}
