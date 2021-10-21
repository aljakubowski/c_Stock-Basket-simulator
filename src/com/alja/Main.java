package com.alja;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        // SIMULATION OF BASKETS FUNCTIONALITY:

        // printing initial stock values
        System.out.println("Stock price list: \n" + stockList.priceList());
        System.out.println(stockList.printStockList());

        // BASKET #1
        System.out.println("***************************************basket #1");
        Basket basket1 = new Basket("Basket 1");

        System.out.println("");
        // showing empty basket and adding items to the basket:
        System.out.println("*");
        System.out.println(basket1.showBasket());
        stockList.addToBasket(basket1, "bread", 10);
        stockList.addToBasket(basket1, "milk", 2);
        stockList.addToBasket(basket1, "cheese", 5);

        // showing added items:
        System.out.println(basket1.showBasket());

        // removing items from basket:
        System.out.println("* *");
        stockList.removeFromBasket(basket1, "bread", 10);
        stockList.removeFromBasket(basket1, "milk", 1);

        // removing items with wrong values
        System.out.println("* * *");
        stockList.removeFromBasket(basket1, "cheese", 10);
        stockList.removeFromBasket(basket1, "cheese", -1);
        stockList.removeFromBasket(basket1, "phone", 1);

        System.out.println(basket1.showBasket());

        // checking out the basket:
        System.out.println("* * * *");
        stockList.sellToCheckOut(basket1);

        // showing basket and stock values:
        System.out.println("* * * * *");
        System.out.println(basket1.showBasket());
        System.out.println(stockList.printStockList());

        // BASKET #2
        System.out.println("***************************************basket #2");
        Basket basket2 = new Basket("Basket 2");

        System.out.println("");
        // adding items to the basket(of max quantities) without checking out :
        System.out.println("*");
        stockList.addToBasket(basket2, "tomato", 200);
        stockList.addToBasket(basket2, "salad", 60);
        System.out.println(basket2.showBasket());

        // BASKET #3
        System.out.println("***************************************basket #3");
        Basket basket3 = new Basket("Basket 3");

        System.out.println("");
        // adding items that are already reserved by "Basket 2" :
        System.out.println("*");
        stockList.addToBasket(basket3, "tomato", 5);

        // adding item that not exists:
        System.out.println("* *");
        stockList.addToBasket(basket3, "banana", 2);

        // adding items and checking out basket 2 and basket 3:
        System.out.println("* * *");
        stockList.addToBasket(basket3, "juice", 40);
        System.out.println(basket3.showBasket());
        stockList.sellToCheckOut(basket3);
        System.out.println(basket3.showBasket());

        stockList.sellToCheckOut(basket2);

        System.out.println(stockList.printStockList());
    }
}
