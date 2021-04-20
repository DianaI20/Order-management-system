package bll;

import dao.OrderDAO;
import model.Order;
import model.Product;

public class OrderBLL {
    private OrderDAO orderDAO;

    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    public int addNewOrder(Order order){
        return  orderDAO.insert(order);
    }
}
