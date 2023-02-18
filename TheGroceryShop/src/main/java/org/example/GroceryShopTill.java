package org.example;

import java.util.*;

public class GroceryShopTill {
    private final List<Groceries> groceriesListTill = new ArrayList<>();
    private final int number;

    public GroceryShopTill(int number) {
        this.number = number;

    }

    public void scanGrocery(Groceries grocery) {
        groceriesListTill.add(grocery);
    }

    public void unscanGrocery(Groceries grocery) {
        if(!groceriesListTill.isEmpty()) {
            groceriesListTill.remove(grocery);
        }
    }


    public List<Groceries> getGroceriesListTill() {
        return groceriesListTill;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        org.example.GroceryShopTill that = (org.example.GroceryShopTill) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
