package org.cloudruid.administrative;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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
            }
            return finalPrice(groceryShopTill);
        }

        else {
            return "Wrong grocery till!";
        }
    }


    //get the final price
    public String finalPrice(GroceryShopTill groceryShopTill) {
        BigDecimal price = calculateFinalPrice(groceryShopTill);
        return priceToStringStatement(price, "aws", "clouds");
    }

    //method to calculate the final price
    private BigDecimal calculateFinalPrice(GroceryShopTill groceryShopTill) {
        BigDecimal price = getPriceWithoutASale(groceryShopTill);
        //reduce price only if there is a special deal
        if(this.specialDealItem != null) {
            price = finalizeSpecialDeal(groceryShopTill, price);
        }
        //reduce price only if there is a properly set two for three deal
        if(this.twoForThreeDealItems.size() == TWO_FOR_THREE_DEAL_CAPACITY) {
            price = finalizeTwoForThreeDeal(groceryShopTill, price);
        }

        return price;
    }

    //get the price before applying any sales
    private BigDecimal getPriceWithoutASale(GroceryShopTill groceryShopTill) {
        BigDecimal price = BigDecimal.ZERO;
        for(Groceries grocery : groceryShopTill.getGroceriesListTill()) {
            price = price.add(availableGroceries.get(grocery));

        }
        return price;
    }

    //From the first three scanned items from the two for three deal get the cheapest item
    private BigDecimal cheapestItemFromTwoForThreeDeal(GroceryShopTill groceryShopTill) {
        BigDecimal min = this.availableGroceries.get(this.twoForThreeDealItems.get(0));
        int count = 0;
        for (Groceries twoForThreeEl : twoForThreeDealItems) {
            for (Groceries grocery : groceryShopTill.getGroceriesListTill()) {
                if (grocery.equals(twoForThreeEl)) {
                    count++;
                    if (min.compareTo(availableGroceries.get(grocery)) > 0) {
                        min = availableGroceries.get(grocery);
                    }
                }
                if (count == TWO_FOR_THREE_DEAL_CAPACITY) {
                    break;
                }
            }
        }

        return min;
    }

    // reduce the price by the cheapest item of the two for three deal
    private BigDecimal reducePriceTwoForThreeDeal(GroceryShopTill groceryShopTill, BigDecimal price) {
        price = price.subtract(cheapestItemFromTwoForThreeDeal(groceryShopTill));
        return price;
    }

    //if the customer has bought all the three products from the sale, reduce his final price by the cheapest item from the first three scanned
    private BigDecimal finalizeTwoForThreeDeal(GroceryShopTill groceryShopTill, BigDecimal price) {
        if (groceryShopTill.getGroceriesListTill().containsAll(this.twoForThreeDealItems)) {
            price = reducePriceTwoForThreeDeal(groceryShopTill, price);
        }
        return price;
    }

    //count the number of special deal items the client wants to buy
    private int countSpecialDealItemsByName(GroceryShopTill groceryShopTill) {
        int specialItemCount = 0;
        for (Groceries grocery : groceryShopTill.getGroceriesListTill()) {
            if (grocery.equals(specialDealItem)) {
                specialItemCount++;
            }
        }
        return specialItemCount;
    }

    //reduce the price by reduceBy times of every second special item
    private BigDecimal reducePriceSpecialDeal(BigDecimal price, int quantityOfSpecialItem, int reduceBy) {
        price = price.subtract(
                (availableGroceries.get(this.specialDealItem).divide(BigDecimal.valueOf(reduceBy)))
                        .multiply(BigDecimal.valueOf(quantityOfSpecialItem))
        );

        return price;
    }


    //reduce the price by 50% of the value of every second special deal item the customer has bought
    private BigDecimal finalizeSpecialDeal(GroceryShopTill groceryShopTill, BigDecimal price) {
        //reduce price only if the customer has bought a special deal item
        if (groceryShopTill.getGroceriesListTill().contains(this.specialDealItem)) {
            int specialItemCount = countSpecialDealItemsByName(groceryShopTill);
            //Every second specialItem gets a 50% discount, so we divide all the specialItems by two
            specialItemCount /= 2;
            price = reducePriceSpecialDeal(price, specialItemCount, 2);
        }
        return price;
    }

    //Method to transform the price to a string statement of it's cost
    private String priceToStringStatement(BigDecimal price, String currency, String subCurrency) {
        String[] currencyStrArr = String.valueOf(price).split("\\.");
        return String.valueOf(String.format("%s %s and %s %s", currencyStrArr[0], currency, currencyStrArr[1], subCurrency));
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


