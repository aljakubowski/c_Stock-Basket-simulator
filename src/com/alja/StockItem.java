package com.alja;

// CLASS REPRESENTING STOCK ITEM
public class StockItem implements Comparable<StockItem>{

    // == fields ==
    private final String name;
    private double price;
    private int quantityStock;
    private int reserved =0;

    // == constructors ==
    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    // == getter and setter methods ==
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getReserved() {
        return this.reserved;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.0){
            this.price = price;
        }
    }

    // == stock item action methods ==
    public void reserve(int reservedQuantity){

        if ( (reservedQuantity > 0) && (reservedQuantity <= (this.quantityStock - this.reserved)) ){
            this.reserved += reservedQuantity;
        } else {
        }
    }

    public void unReserve(int unReservedQuantity){

        if ( this.reserved >= unReservedQuantity ){
            this.reserved -= unReservedQuantity;
        } else {
        }
    }

    public void adjustStock(int quantity){
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0){
            this.quantityStock = newQuantity;
        }
    }

    // == overridden methods ==
    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");

        if (obj == this){
            return true;
        }

        if ( (obj == null) || (obj.getClass() != this.getClass()) ){
            return false;
        }

        String objName = ( (StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() +31;
    }

    @Override
    public int compareTo(StockItem o) {

        if (this == o){
            return 0;
        }
        if (o != null){
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name; // + " : price " + this.price;
    }
}