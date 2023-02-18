package org.example;

import java.math.BigDecimal;
import java.util.*;

public class GroceryShopTill {
    private List<Groceries> groceriesListTill = new ArrayList<>();
    private int number;

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

    //get the final price
    public String finalPrice(Map<Groceries, BigDecimal> availableGroceries, List<Groceries> twoForThreeDealList, int maxCapacity,
                             Groceries specialDealItem, BigDecimal specialDealItemPrice) {
        BigDecimal price = getPriceWithoutASale(availableGroceries);
        //reduce price only if there is a special deal
        if(specialDealItem != null) {
            price = finalizeSpecialDeal(price, specialDealItem, specialDealItemPrice);
        }
       if(twoForThreeDealList.size() == maxCapacity) {
            price = finalizeTwoForThreeDeal(availableGroceries, twoForThreeDealList, maxCapacity, price);
        }
        return priceToStringStatement(price, "aws", "clouds");
    }

    //get the price before applying any sales
    private BigDecimal getPriceWithoutASale(Map<Groceries, BigDecimal> availableGroceries) {
        BigDecimal price = BigDecimal.ZERO;
        for(Groceries grocery : groceriesListTill) {
            price = price.add(availableGroceries.get(grocery));

        }
        return price;
    }

    //From the first three scanned items from the two for three deal get the cheapest item
    private BigDecimal cheapestItemFromTwoForThreeDeal(Map<Groceries, BigDecimal> availableGroceries,  List<Groceries> twoForThreeDealList, int maxCapacity) {
        BigDecimal min = availableGroceries.get(twoForThreeDealList.get(0));
        int count = 0;

        for (Groceries twoForThreeEl : twoForThreeDealList) {
            for (Groceries grocery : this.groceriesListTill) {
                if (grocery.equals(twoForThreeEl)) {
                    count++;
                    if (min.compareTo(availableGroceries.get(grocery)) > 0) {
                        min = availableGroceries.get(grocery);
                    }
                }
                if (count == maxCapacity) {
                    break;
                }
            }
        }

        return min;
    }

    // reduce the price by the cheapest item of the two for three deal
    private BigDecimal reducePriceTwoForThreeDeal(Map<Groceries, BigDecimal> availableGroceries,  List<Groceries> twoForThreeDealList,
                                                  int maxCapacity, BigDecimal price) {
        price = price.subtract(cheapestItemFromTwoForThreeDeal(availableGroceries, twoForThreeDealList, maxCapacity));
        return price;
    }

    //if the customer has bought all the three products from the sale, reduce his final price by the cheapest item from the first three scanned
    private BigDecimal finalizeTwoForThreeDeal(Map<Groceries, BigDecimal> availableGroceries,  List<Groceries> twoForThreeDealList,
                                               int maxCapacity, BigDecimal price) {
        if (this.groceriesListTill.containsAll(twoForThreeDealList)) {
            price = reducePriceTwoForThreeDeal(availableGroceries, twoForThreeDealList, maxCapacity, price);
        }
        return price;
    }



    //count the number of special deal items the client wants to buy
    private int countSpecialDealItemsByName(Groceries specialDealItem) {
        int specialItemCount = 0;
        for (Groceries grocery : groceriesListTill) {
            if (grocery.equals(specialDealItem)) {
                specialItemCount++;
            }
        }
        return specialItemCount;
    }

    //reduce the price by reduceBy times of every second special item
    private BigDecimal reducePriceSpecialDeal(BigDecimal price, int quantityOfSpecialItem, int reduceBy, BigDecimal specialDealItemPrice) {
        price = price.subtract(
                (specialDealItemPrice.divide(BigDecimal.valueOf(reduceBy)))
                        .multiply(BigDecimal.valueOf(quantityOfSpecialItem))
        );

        return price;
    }


    //reduce the price by 50% of the value of every second special deal item the customer has bought
    private BigDecimal finalizeSpecialDeal(BigDecimal price, Groceries specialDealItem, BigDecimal specialDealItemPrice) {
        //reduce price only if the customer has bought a special deal item
        if (this.groceriesListTill.contains(specialDealItem)) {
            int specialItemCount = countSpecialDealItemsByName(specialDealItem);
            //Every second specialItem gets a 50% discount, so we divide all the specialItems by two
            specialItemCount /= 2;
            price = reducePriceSpecialDeal(price, specialItemCount, 2, specialDealItemPrice);
        }
        return price;
    }

    //Method to transform the price to a string statement of it's cost
    private String priceToStringStatement(BigDecimal price, String currency, String subCurrency) {
        String[] currencyStrArr = String.valueOf(price).split("\\.");
        return String.valueOf(String.format("%s %s and %s %s", currencyStrArr[0], currency, currencyStrArr[1], subCurrency));
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
        GroceryShopTill that = (GroceryShopTill) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
