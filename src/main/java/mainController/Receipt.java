package mainController;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private static int index = 1;

    public void printReceipt(Order order, ClientBLL clientBLL, ProductBLL productBLL) {
        Client client = clientBLL.findClientById(order.getClientId());
        Product product = productBLL.findProductById(order.getProductId());
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            FileWriter myWriter = new FileWriter("BillNo" + index + ".txt");
            myWriter.write("Order id: " + index + "\n");
            myWriter.write("Placed at: " + dtf.format(LocalDateTime.now()) + "\n");
            myWriter.write("Client details:");
            myWriter.write("Address:" + client.getAddress() + "\n");
            myWriter.write("Phone number: " + client.getPhoneNumber() + "\n");
            myWriter.write("Email: " + client.getEmail() + "\n");
            myWriter.write("Product: " + product.getName() + "\n");
            myWriter.write("Quantity: " + order.getQuantity() + "\n");
            myWriter.write("Price per product:" + product.getPrice() + "\n");
            myWriter.write("Total: " + order.getTotalPrice() + "\n");
            myWriter.close();
            index++;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in printing the receipt.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
