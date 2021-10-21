package com.alja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public int addToBasket(StockItem item, int quantity) {

        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {

        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket - quantity);

            if (item.getReserved() == 0) {
                list.remove(item);
            }
            return inBasket;
        }
        return 0;
    }

    public void checkOut() {

        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            totalCost += item.getKey().getPrice() * item.getValue();
        }

        for (Map.Entry<StockItem, Integer> eachItem : list.entrySet()) {
            removeFromBasket(eachItem.getKey(), eachItem.getValue());
            eachItem.getKey().unReserve(eachItem.getKey().getReserved());
        }
        list.clear();
        System.out.println("paid: " + String.format("%.2f", totalCost) + "$");
    }

    public Map<StockItem, Integer> itemsInBasket() {
        return Collections.unmodifiableMap(list);
    }

    public List<String> itemsByNameInBasket() {
        ArrayList<String> itemNamesInBasket = new ArrayList<>();
        for (StockItem item : list.keySet()) {
            itemNamesInBasket.add(item.getName());
        }
        return itemNamesInBasket;
    }

    public String showBasket() {
        if (list.isEmpty()) {
            String e = "'" + this.name + "' is empty";
            return e;
        } else {
            String s = "\n Shopping basket '" + name + "' contains: \n";
            double totalCost = 0.0;
            for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
                s = s + "\t" + item.getKey() + " * " + item.getValue() +
                        " (" + String.format("%.2f", item.getValue() * item.getKey().getPrice()) + ") " + "\n";
                totalCost += item.getKey().getPrice() * item.getValue();
            }
            return s + "\t= total cost " + String.format("%.2f", totalCost) + "$";
        }
    }
}