package model;

import java.util.List;

public class Product {

    private int productID;
    private String name;
    private float price;
    private String description;
    private int leftInStock;

    public Product(String name, float price, String description, int leftInStock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.leftInStock  = leftInStock;
    }
    public Product(List<String > fields) {
        this.name = fields.get(0);
        this.price = Float.parseFloat(fields.get(1));
        this.description = fields.get(2);
        this.leftInStock  = Integer.parseInt(fields.get(3));
    }
    public Product() {

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLeftInStock() {
        return leftInStock;
    }

    public void setLeftInStock(int leftInStock) {
        this.leftInStock = leftInStock;
    }

    @Override
    public String toString() {
        return
                "ID =" + productID +
                        ", Name: " + name + ", Price: "+ price;
    }
}
