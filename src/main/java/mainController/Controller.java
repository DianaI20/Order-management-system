package mainController;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
import presentation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private MainFrame mainFrame;
    private ClientFrame clientFrame;
    private ProductFrame productFrame;
    private OrderFrame orderFrame;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private Receipt receipt;


    public Controller() {
        mainFrame = new MainFrame("Order Management");
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        receipt = new Receipt();
        addMainFrameAction();
    }

    public void addMainFrameAction() {
        mainFrame.getClientOperationsButton().addActionListener(e -> {
            clientFrame = new ClientFrame("Client Operations");
            addClientAction();
            editClientAction();
            viewClientsAction();
            deleteClientAction();
        });
        mainFrame.getProductOperationsButton().addActionListener(e -> {
            productFrame = new ProductFrame("Product Operations",productBLL.getTableOfProducts());
            addProductAction();
            editProductAction();
            viewProductsAction();
            deleteProductAction();
        });
        mainFrame.getOrdersButton().addActionListener(e -> {
            orderFrame = new OrderFrame("Place order", productBLL.getProductItems(), clientBLL.getClientItems());
            addOrderAction();
            viewOrdersAction();
        });}
    /**
     * Method to add action for the add button on the client frame.
     */
    public void addClientAction() {
        clientFrame.getAddClientButton().addActionListener(f -> {
            Client client = new Client(getClientTextFields());
            if (clientBLL.addNewClient(client) != -1) {
                JOptionPane.showMessageDialog(null, "Client added successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                refreshClientFrame();
            }
        });
    }

    public void editClientAction() {
        clientFrame.getEditClientButton().addActionListener(e -> {
            try {
                int id = Integer.parseInt(clientFrame.getIdField().getText());
                List<String> fields = getClientTextFields();
                Client client = clientBLL.findClientById(id);
                if (fields.get(0).equals("") == false) {
                    client.setName(fields.get(0));
                }
                if (fields.get(1).equals("") == false) {
                    client.setAddress(fields.get(1));
                }
                if (fields.get(2).equals("") == false) {
                    client.setEmail(fields.get(2));
                }
                if (fields.get(3).equals("") == false) {
                    client.setPhoneNumber(fields.get(3));
                }
                clientBLL.editClient(client);
                JOptionPane.showMessageDialog(null, "Client edited successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                refreshClientFrame();
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Invalid id.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void viewClientsAction() {
        clientFrame.getViewClientButton().addActionListener(e -> {
            clientFrame.getTablePanel().removeAll();
            clientFrame.getTablePanel().add(new JScrollPane(clientBLL.getTableOfClients()));
            clientFrame.getTablePanel().revalidate();
            clientFrame.getTablePanel().repaint();
        });
    }

    public void deleteClientAction() {
        clientFrame.getDeleteClientButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(clientFrame.getIdField().getText());
                Client temp = new Client();
                temp.setIdClient(id);
                clientBLL.deleteClient(temp);
                JOptionPane.showMessageDialog(null, "Client removed.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                refreshClientFrame();
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Invalid id.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    /**
     * Method to add action for the add button of the product frame
     */
    public void addProductAction() {
        productFrame.getAddProductButton().addActionListener(f -> {
            try{
            Product product = new Product(getProductTextFields());
            if (productBLL.addNewProduct(product) != -1) {
                JOptionPane.showMessageDialog(null, "Product added successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                refreshProductFrame();
            }}catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error in numerical fields.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Method to add action for the edit button of the product frame
     */
    public void editProductAction() {
        productFrame.getEditProductButton().addActionListener(e -> {
            try {
                int id = Integer.parseInt(productFrame.getIdField().getText());
                List<String> fields = getProductTextFields();
                Product product = productBLL.findProductById(id);
                if (fields.get(0).equals("") == false) {
                    product.setName(fields.get(0));
                }
                if (fields.get(1).equals("") == false) {
                    product.setPrice(Float.parseFloat(fields.get(1)));
                }
                if (fields.get(2).equals("") == false) {
                    product.setDescription(fields.get(2));
                }
                if (fields.get(3).equals("") == false) {
                    product.setLeftInStock(Integer.parseInt(fields.get(3)));
                }
                productBLL.editProduct(product);
                refreshProductFrame();
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Invalid id.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void viewProductsAction() {
        productFrame.getViewProductButton().addActionListener(e -> {
            productFrame.getTablePanel().removeAll();
            JTable table = productBLL.getTableOfProducts();
            table.setFillsViewportHeight(true);
            productFrame.getTablePanel().add(new JScrollPane(table));
            productFrame.getTablePanel().revalidate();
            productFrame.getTablePanel().repaint();
        });
    }

    public void deleteProductAction() {
        productFrame.getDeleteProductButton().addActionListener(e->{
            try {
                int id = Integer.parseInt(productFrame.getIdField().getText());
                Product temp = new Product();
                temp.setProductID(id);
                productBLL.deleteProduct(temp);
                JOptionPane.showMessageDialog(null, "Product removed", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                refreshProductFrame();
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Invalid id.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void refreshClientFrame() {
        clientFrame.getIdField().setText("");
        clientFrame.getAddressField().setText("");
        clientFrame.getNameField().setText("");
        clientFrame.getPhoneNumber().setText("");
        clientFrame.getMailField().setText("");
    }

    public void refreshProductFrame() {
        productFrame.getIdField().setText("");
        productFrame.getNameField().setText("");
        productFrame.getDescriptionField().setText("");
        productFrame.getPriceField().setText("");
        productFrame.getQuantityField().setText("");
    }

    /**
     * Method to get the values from the text field
     * @return A list of strings with the values taken from the GUI
     */
    public List<String> getClientTextFields() {
        List<String> fields = new ArrayList<>();
        fields.add(clientFrame.getNameField().getText());
        fields.add(clientFrame.getAddressField().getText());
        fields.add(clientFrame.getMailField().getText());
        fields.add(clientFrame.getPhoneNumber().getText());
        return fields;
    }

    public List<String> getProductTextFields() {
        List<String> fields = new ArrayList<>();
        fields.add(productFrame.getNameField().getText());
        fields.add(productFrame.getPriceField().getText());
        fields.add(productFrame.getDescriptionField().getText());
        fields.add(productFrame.getQuantityField().getText());
        return fields;
    }

    /**
     * Method to get the id from a string
     * @param s The selected item from the combobox
     * @return The id
     */
    public int getIdFromText(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        if (m.find())
            return Integer.parseInt(m.group());
        else
            return -1;
    }

    public void addOrderAction() {
        orderFrame.getPlaceOrderButton().addActionListener(e -> {
            int idProd = getIdFromText(orderFrame.getProductBox().getSelectedItem().toString());
            int idClient = getIdFromText(orderFrame.getSelectClientBox().getSelectedItem().toString());
            try {
                int quantity = Integer.parseInt(orderFrame.getQuantityField().getText());
                if (quantity > 0) {
                    Product product = productBLL.findProductById(idProd);
                    int leftInStock = product.getLeftInStock() - quantity;
                    if (leftInStock >= 0) {
                        float totalPrice = product.getPrice() * quantity;
                        Order order = new Order(totalPrice, idClient, idProd, quantity);
                        product.setLeftInStock(leftInStock);
                        productBLL.editProduct(product);
                        orderBLL.addNewOrder(order);
                        receipt.printReceipt(order, clientBLL, productBLL);
                        orderFrame.getQuantityField().setText("");
                        JOptionPane.showMessageDialog(null, "Order placed!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Stock unavailable.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Invalid quantity.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }catch (NumberFormatException g){
                JOptionPane.showMessageDialog(null, "Invalid quantity.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } }); }

    public void viewOrdersAction(){
        orderFrame.getViewAllOrdersButton().addActionListener(e -> {
            orderFrame.getTablePanel().removeAll();
            orderFrame.getTablePanel().add(new JScrollPane(orderBLL.getTableOfOrders()));
            orderFrame.getTablePanel().revalidate();
            orderFrame.getTablePanel().repaint();
        });
        orderFrame.getViewClientsButton().addActionListener(e->{
            orderFrame.getTablePanel().removeAll();
            orderFrame.getTablePanel().add(new JScrollPane(clientBLL.getTableOfClients()));
            orderFrame.getTablePanel().revalidate();
            orderFrame.getTablePanel().repaint();
        });
        orderFrame.getViewProductsButton().addActionListener(e->{
            orderFrame.getTablePanel().removeAll();
            orderFrame.getTablePanel().add(new JScrollPane(productBLL.getTableOfProducts()));
            orderFrame.getTablePanel().revalidate();
            orderFrame.getTablePanel().repaint();
        });
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
