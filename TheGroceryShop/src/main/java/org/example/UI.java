package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    public int numberOfGroceriesToAddToTheShop() {
        int n = 1;
        do {
            if(n <= 0) {
                System.out.println("The number of items to be added must be a positive value!");
            }
            System.out.println("Enter the number of items you are going to add to the shop: ");
            n = scanner.nextInt();
        }
        while(n <= 0);
        return n;
    }

    public BigDecimal setGroceryPrice(int n) {
        BigDecimal price = BigDecimal.valueOf(-1);
        do {
            System.out.println("Enter the price of the " + n + " item: ");
            price = new BigDecimal(scanner.next());

        }

        while(!checkPrice(price));
        return price;
    }

    public boolean checkPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Price must be positive!");
            return false;
        }
        if(price.scale() > 2) {
            System.out.println("Such a price does not exist!");
            return false;
        }

        return true;
    }

    public Groceries createGrocery(int n) {
        System.out.println("Enter the name of the " + n + " item: ");
        String name = scanner.next();
        Groceries grocery = new Groceries(name);
        return grocery;
    }

    public boolean addGrocery(int n, Admin admin) {// nz doopravi go, proverqvqi za greshki alabala
        boolean wentOk = true;
        for(int i = 0; i < n; i++) {
            Groceries grocery = this.createGrocery(i+1);
            BigDecimal price = this.setGroceryPrice(i+1);
            wentOk = admin.addGroceryToTheShop(grocery, price);
            if(!wentOk) {
                System.out.println("This grocery is already added to the shop!");
                return false;
            }
        }

        return true;
    }



    public boolean askForSpecialItem(String dealType) {
        System.out.println("Do you want to set a " + dealType + " deal item for the store(yes/no)?");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }

    public Groceries setSpecialDealItem(Admin admin) {
        Groceries grocery = null;
        if(askForSpecialItem("Special")) {
            boolean setSpecial = true;
            do {
                if(!setSpecial) {
                    System.out.println("This grocery is not available in this shop!");
                }
                System.out.println("Which item will participate in the special deal?");
                String item = scanner.next();
                grocery = new Groceries(item);
                setSpecial = admin.setSpecialDealItemToTheShop(grocery);
            }
            while(!setSpecial);
        }

        return grocery;
    }

    public List<Groceries> setTwoForThreeDealItems(Admin admin) {
        List<Groceries> twoForThreeDeal = new ArrayList<>();
        if(askForSpecialItem("Two for Three")) {
            for(int i = 1 ; i <= admin.getCapacityForTwoForThreeOfTheShop(); i++) {
                boolean setTwoForThree = true;
                do
                {
                    if(!setTwoForThree) {
                        System.out.println("This grocery is not available in this shop!");
                    }
                    System.out.println("Enter the " + i + " item that will participate in the two for three deal?");
                    String item = scanner.next();
                    Groceries grocery = new Groceries(item);
                    setTwoForThree = admin.checkIfGroceryIsAvailable(grocery);
                    if (setTwoForThree) {
                        admin.addToTheTwoForThreeDeal(grocery);
                        twoForThreeDeal.add(grocery);
                    }

                }
                while (!setTwoForThree);
            }
        }

        return twoForThreeDeal;
    }


}
