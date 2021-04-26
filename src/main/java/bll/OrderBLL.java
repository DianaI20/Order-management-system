package bll;

import bll.validators.OrderValidator;
import dao.OrderDAO;
import model.Order;
import model.Product;

import javax.swing.*;

public class OrderBLL {
    private OrderDAO orderDAO;
    private OrderValidator orderValidator;
    public OrderBLL() {
        orderValidator = new OrderValidator();
        orderDAO = new OrderDAO();
    }

    /**
     *
     * @param order The order to be inserted
     * @return The id of the inserted order if the order was inserted successfully and -1 otherwise
     */
    public int addNewOrder(Order order){
        try {
            orderValidator.validate(order);
            return orderDAO.insert(order);
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Invalid quantity.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    /**
     *
     * @return JTable with all the orders
     */
    public JTable getTableOfOrders(){
        return orderDAO.createTable(orderDAO.findAll());
    }

}
