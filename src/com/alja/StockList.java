package com.alja;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

// CLASS REPRESENTING STOCK LIST
public class StockList {

    private final Map<String, StockItem> list;
    private final String message = "\t>StockList: ";

    public StockList() {
        this.list = new LinkedHashMap<>();
        initialStock();
    }

    private int addInitialStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public void addToBasket(Basket basket, String item, int quantity) {

        StockItem itemToReserve = list.getOrDefault(item, null);

        if ((itemToReserve == null)) {
            System.out.println(message + "We do not sell '" + item + "'");
        } else if (quantity <= 0) {
            System.out.println(message + "Failed, inappropriate quantity");
        } else {

            int inStockMinusReserved = itemToReserve.quantityInStock() - itemToReserve.getReserved();

            if (inStockMinusReserved < quantity) {
                System.out.println(message + "Not reserved. Not enough '" + itemToReserve.getName() + "' items available");
            } else {
                basket.addToBasket(itemToReserve, quantity);
                itemToReserve.reserve(quantity);
                inStockMinusReserved -= quantity;

                System.out.println(message + basket.getName() + ": " + message + " Reserved " +
                        quantity + " '" + itemToReserve.getName() + "' | " + inStockMinusReserved + " items left");
            }
        }
    }

    public boolean removeFromBasket(Basket basket, String item, int quantity) {

        if (!basket.itemsByNameInBasket().contains(item) || quantity < 1) {
            System.out.println(message + " Failed: no such item or inappropriate quantity");
            return false;
        }

        StockItem itemToUnReserve = list.get(item);

        if (basket.itemsInBasket().get(itemToUnReserve) < quantity) {
            System.out.println(message + " Failed: not enough '" + item + "' items in basket");
            return false;
        }

        itemToUnReserve.unReserve(quantity);
        System.out.println(message + " Unreserved " + quantity + " '" + itemToUnReserve.getName() + "' items.");
        basket.removeFromBasket(itemToUnReserve, quantity);
        return true;
    }

    public void sellToCheckOut(Basket basketToCheckOut) {

        for (Map.Entry<StockItem, Integer> toSell : basketToCheckOut.itemsInBasket().entrySet()) {
            StockItem inStock = list.get(toSell.getKey().getName());
            inStock.adjustStock(-toSell.getValue());
        }
        System.out.print(message + "'" + basketToCheckOut.getName() + "' checkout: ");
        basketToCheckOut.checkOut();
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public String printStockList() {
        String s = "\nStock List\n";
        double totalCost = 0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {

            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + "\t" + stockItem + ":\t there are " + stockItem.quantityInStock() + " in stock." +
                    " Value of the items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + "Total Stock Value " + String.format("%.2f", totalCost) + "$";
    }

    private void initialStock() {
        StockItem temp = new StockItem("bread", 0.86, 10);
        addInitialStock(temp);
        temp = new StockItem("cake", 1.1, 2);
        addInitialStock(temp);
        temp = new StockItem("milk", 1.29, 40);
        addInitialStock(temp);
        temp = new StockItem("tomato", 0.23, 200);
        addInitialStock(temp);
        temp = new StockItem("cheese", 4.15, 60);
        addInitialStock(temp);
        temp = new StockItem("cheese", 2.05, 10);
        addInitialStock(temp);
        temp = new StockItem("juice", 3.0, 120);
        addInitialStock(temp);
        temp = new StockItem("cherry", 0.1, 500);
        addInitialStock(temp);
        temp = new StockItem("salad", 1.9, 60);
        addInitialStock(temp);
        temp = new StockItem("bread", 5.14, 10);
        addInitialStock(temp);
    }
}