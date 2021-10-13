package com.alja;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

// CLASS REPRESENTING STOCK LIST
public class StockList {

    // == fields ==
    private final Map<String, StockItem> list;

    // == constructors ==
    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    // == stock list methods ==

    public int addStock(StockItem item){
        if (item != null){

            StockItem inStock = list.getOrDefault(item.getName(), item);

            if (inStock != item){
                item.adjustStock(inStock.quantityInStock());
            }
            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public int addToReserved(String item, int quantity){

        StockItem toReserve = list.getOrDefault(item, null);
        int inStockMinusReserved = toReserve.quantityInStock() - toReserve.getReserved();

        if ( (toReserve != null) && inStockMinusReserved >= quantity){
            toReserve.reserve(quantity);
            inStockMinusReserved -= quantity;
            System.out.println("StockList called. Reserved " +
                    quantity + " '" + toReserve.getName() + " | "+ inStockMinusReserved + " items left" );
            return quantity;
        }
        System.out.println("StockList called. Not reserved. Not enough '" + toReserve.getName() + "' items");
        return 0;
    }

    public int removeFromReserved(String item, int quantity){

        StockItem toUnReserve = list.getOrDefault(item, null);


        if ( (toUnReserve != null) && toUnReserve.getReserved() >= quantity){
            toUnReserve.unReserve(quantity);
            System.out.println("StockList called. Unreserved " + quantity + " '" + toUnReserve.getName() + "' items.");
            return quantity;
        }
        System.out.println("StockList called. Not enough '" + toUnReserve.getName() + "' items to remove");
        return 0;
    }

    public void sellToCheckOut(Basket cartToCheckOut){

        for (Map.Entry<StockItem, Integer> toSell : cartToCheckOut.items().entrySet()){
            sellStock(toSell.getKey().getName(), toSell.getValue());
        }
    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if ( (inStock !=null) && (inStock.quantityInStock() >= quantity) && (quantity > 0) ){
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key){
        return list.get(key);
    }

    public Map<String, Double> priceList(){
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet() ){
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost =0;
        for (Map.Entry<String, StockItem> item : list.entrySet()){

            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + "\t" + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of the items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }
        return s +"Total Stock Value " + String.format("%.2f", totalCost);
    }
}