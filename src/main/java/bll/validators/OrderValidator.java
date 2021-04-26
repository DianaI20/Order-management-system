package bll.validators;

import model.Order;

import javax.swing.*;

public class OrderValidator implements Validator<Order> {

    @Override
    public void validate(Order order) {
        if(order.getQuantity() < 0) {
            throw new IllegalArgumentException();
        }
    }
}
