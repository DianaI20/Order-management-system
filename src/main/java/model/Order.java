package model;

public class Order {
    private int idOrder;
    private float totalPrice;
    private int clientId;
    private int productId;
    private int quantity;

    public Order() {

    }

    public Order(float totalPrice, int clientId, int productId, int quantity) {
        this.totalPrice = totalPrice;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
