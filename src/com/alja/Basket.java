package com.alja;

import java.util.*;

// CLASS REPRESENTING BASKET
public class Basket {

    // == fields ==
    private final String name;
    private final Map<StockItem, Integer> list;

    // == constructors ==
    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToCart(StockItem item, int quantity){

        if ( (item != null) && (quantity>0) ){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    // == basket methods ==
    public int removeFromCart(StockItem item, int quantity){

        if ( (item != null) && (quantity>0) ){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket - quantity);

            if ( item.getReserved() == 0 ){
                list.remove(item);
            }
            return inBasket;
        }

        return 0;

    }

    public void checkOut(){

        double totalCost =0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()){
            totalCost += item.getKey().getPrice() * item.getValue();
        }

        for ( Map.Entry <StockItem, Integer> eachItem : list.entrySet()){
            removeFromCart(eachItem.getKey(), eachItem.getValue());
            eachItem.getKey().unReserve(eachItem.getKey().getReserved());
        }
        list.clear();

        System.out.println("basket check out, paid: " + String.format("%.2f", totalCost) + "$" );
    }

    public Map<StockItem, Integer> items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {

        if (list.isEmpty()) {
            String e = "cart is empty";
            return e;
        } else {
            String s = "\n Shopping basket '" + name + "' contains: \n";
            double totalCost = 0.0;
            for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
                s = s + item.getKey() + " * " + item.getValue() +
                        " (" + String.format("%.2f", item.getValue() * item.getKey().getPrice()) + ") " + "\n";
                totalCost += item.getKey().getPrice() * item.getValue();
            }
            return s + "= total cost " + String.format("%.2f", totalCost) + "$";
        }
    }
}