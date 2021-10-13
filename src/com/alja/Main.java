package com.alja;


public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        // ADD ITEMS TO STOCK ////////////////////////////////////////////////////
        StockItem temp = new StockItem("bread", 0.86, 10);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.1, 2);
        stockList.addStock(temp);
        temp = new StockItem("milk", 1.29, 40);
        stockList.addStock(temp);
        temp = new StockItem("tomato", 0.23, 200);
        stockList.addStock(temp);
        temp = new StockItem("cheese", 4.15, 60);
        stockList.addStock(temp);
        temp = new StockItem("cheese", 2.05, 10);
        stockList.addStock(temp);
        temp = new StockItem("juice", 3.0, 120);
        stockList.addStock(temp);
        temp = new StockItem("cherry", 0.1, 500);
        stockList.addStock(temp);
        temp = new StockItem("salad", 1.9, 60);
        stockList.addStock(temp);
        temp = new StockItem("ketchup", 5.14, 10);
        stockList.addStock(temp);

        System.out.println(stockList);

        
        // SIMULATION OF BASKETS /////////////////////////////////////////////////

            // BASKET #1
        System.out.println("*********************************cart #1");
        Basket myCart1 = new Basket("Cart 1");

        System.out.println("");
        addToCart(myCart1, "ketchup", 10);
        addToCart(myCart1, "milk", 2);
        addToCart(myCart1, "cheese", 5);
        System.out.println(myCart1);

        System.out.println("#");
        removeFromCart(myCart1, "ketchup", 10);
        removeFromCart(myCart1, "milk", 1);
        System.out.println(myCart1);

        System.out.println("# #");
        addToCart(myCart1, "ketchup", 9);
        checkOut(myCart1);
        System.out.println(myCart1);

        System.out.println(stockList);

            // BASKET #2
        System.out.println("*********************************cart #2");
        Basket myCart2 = new Basket("Cart 2");

        System.out.println("");

        addToCart(myCart2, "ketchup", 1);
        addToCart(myCart2, "milk", 40);
        addToCart(myCart2, "cheese", 5);
        System.out.println(myCart2);

            // BASKET #3
        System.out.println("*********************************cart #3");
        Basket myCart3 = new Basket("Cart 3");

        System.out.println("");

        addToCart(myCart3, "ketchup", 1);
        removeFromCart(myCart3,"milk", 2);
        addToCart(myCart3, "salad", 40);
        System.out.println(myCart3);

        checkOut(myCart3);
        System.out.println(myCart3);

        System.out.println(stockList);

    }

    // BASKET FUNCTIONALITY ////////////////////////////////////////////////////

    public static int addToCart(Basket basket, String item, int quantity){

        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We do not sell " + item);
            return 0;
        }
        if (stockList.addToReserved(item, quantity) != 0){
            basket.addToCart(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    public static int removeFromCart(Basket basket, String item, int quantity){

        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We do not sell " + item);
            return 0;
        }
        if (stockList.removeFromReserved(item, quantity) != 0){
            basket.removeFromCart(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    public static void checkOut(Basket cart) {
        stockList.sellToCheckOut(cart);
        cart.checkOut();
    }
}
