package org.example;

import java.math.BigDecimal;
import java.util.*;

public class GroceryShop {
    private HashMap<Groceries, BigDecimal> availableGroceries = new HashMap<>();

    private List<GroceryShopTill> groceryShopTillList = new ArrayList<>();

    private static final int TWO_FOR_THREE_DEAL_CAPACITY = 3;
    private List<Groceries> twoForThreeDealItems = new ArrayList<>(TWO_FOR_THREE_DEAL_CAPACITY );
    private Groceries specialDealItem;

    public GroceryShop() {
        this.specialDealItem = null;
    }

    public boolean addGrocery(Groceries grocery, BigDecimal price) {
        if(!availableGroceries.containsKey(grocery)) {
            availableGroceries.put(grocery, price);
            return true;
        }

        return false;
    }


    public boolean setSpecialDeal(Groceries grocery) {
        if(availableGroceries.containsKey(grocery)) {
            this.specialDealItem = grocery;
            return  true;
        }
        return false;
    }

    public boolean addToTwoForThreeDeal(Groceries grocery) {
        if(availableGroceries.containsKey(grocery)) {
            if(!twoForThreeDealItems.contains(grocery)) {
                if(this.twoForThreeDealItems.size() < TWO_FOR_THREE_DEAL_CAPACITY) {
                    twoForThreeDealItems.add(grocery);
                    return true;
                }
            }
        }

        return false;
    }

    public String sellGroceries(GroceryShopTill groceryShopTill, List<Groceries> groceries) {
        if(groceryShopTillList.contains(groceryShopTill)) {
            for(Groceries grocery : groceries) {
                groceryShopTill.scanGrocery(grocery);
            };
            return groceryShopTill.finalPrice(this.availableGroceries, this.twoForThreeDealItems, TWO_FOR_THREE_DEAL_CAPACITY,
                    this.specialDealItem, getPriceOfAGrocery(this.specialDealItem));
        }

        else {
            return "Wrong grocery till!";
        }
    }
    public static int getCapacity() {
        return GroceryShop.TWO_FOR_THREE_DEAL_CAPACITY;
    }

    public HashMap<Groceries, BigDecimal> getAvailableGroceries() {
        return availableGroceries;
    }

    public Groceries getSpecialDealItem() {
        return specialDealItem;
    }

    public List<Groceries> getTwoForThreeDealItems() {
        return twoForThreeDealItems;
    }

    public void addAGroceryTill(GroceryShopTill groceryShopTill) {
        groceryShopTillList.add(groceryShopTill);
    }

    public BigDecimal getPriceOfAGrocery(Groceries grocery) {
        return this.availableGroceries.get(grocery);
    }
}

