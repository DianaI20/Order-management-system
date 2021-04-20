package mainController;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
import presentation.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private MainFrame mainFrame;

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
        addMainFrameActions();
    }

    /**
     * Function to add actions for buttons in the main frame
     */
    private void addMainFrameActions() {

        mainFrame.getClientOperationsButton().addActionListener(e -> {
            ClientFrame clientFrame = new ClientFrame("Client Operations");
            addClientFrameActions(clientFrame);
        });
        mainFrame.getProductOperationsButton().addActionListener(e -> {
            ProductFrame productFrame = new ProductFrame("Product Operations");
            addProductFrameActions(productFrame);
        });
        mainFrame.getOrdersButton().addActionListener(e -> {
            OrderFrame orderFrame = new OrderFrame("Place order", productBLL.getProductItems(), clientBLL.getClientItems());
            addOrderAction(orderFrame);
        });
    }

    /**
     * Function to retrieve the parameters from the text fields and create an object of type Client
     *
     * @param clientFrame - The frame which deals with  information about the client
     */
    private void addClientFrameActions(ClientFrame clientFrame) {
        addDeleteAndViewClient(clientFrame);
        clientFrame.getAddClientButton().addActionListener(e -> {
            AddClientFrame addClientFrame = new AddClientFrame("Add client ");
            addClientFrame.getAddClientButton().addActionListener(f -> {
                    Client client = new Client(addClientFrame.getNameField().getText(), addClientFrame.getAdressField().getText(),
                        addClientFrame.getEmailField().getText(), addClientFrame.getPhoneNumberField().getText());
                        clientBLL.addNewClient(client);
                        addClientFrame.getAdressField().setText("");
                        addClientFrame.getNameField().setText("");
                        addClientFrame.getPhoneNumberField().setText("");
                        addClientFrame.getEmailField().setText("");
                JOptionPane.showMessageDialog(null, "Client added successfully.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            });
        });
        clientFrame.getEditClientButton().addActionListener(e -> {
            EditFrame editFrame = new EditFrame("Edit entry", clientBLL.getClientItems());
            addEditClientButtonAction(editFrame);
        });
    }

    /** Function to insert a product in the database
     * @param productFrame
     */
    private void addProductFrameActions(ProductFrame productFrame) {

        addDeleteAndViewProduct(productFrame);

        productFrame.getAddProductButton().addActionListener(e -> {
            AddProductFrame addProductFrame = new AddProductFrame("Add product");


                addProductFrame.getAddProductButton().addActionListener(f -> {
                try {
                    float price = Float.parseFloat(addProductFrame.getPriceField().getText());
                    int quantity = Integer.parseInt(addProductFrame.getQuantityField().getText());
                    if(price > 0 && quantity > 0){

                    Product product = new Product(addProductFrame.getNameField().getText(), price,
                            addProductFrame.getDescriptionField().getText(), quantity );
                    productBLL.addNewProduct(product);
                    addProductFrame.getNameField().setText("");
                    addProductFrame.getDescriptionField().setText("");
                    addProductFrame.getQuantityField().setText("");
                    addProductFrame.getPriceField().setText("");
                        JOptionPane.showMessageDialog(null, "Product added", "Success", JOptionPane.INFORMATION_MESSAGE);

                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
                });

        });
        productFrame.getEditProductButton().addActionListener(e -> {
            String[] labels = new String[]{"Name", "Price", "Description", "Stock:"};
            EditFrame editFrame = new EditFrame("Edit entry", productBLL.getProductItems(), labels);
            addEditProductButtonAction(editFrame);
        });
    }

    /** Function to add actionListener for edit button for clients
     * @param editclientFrame
     */
    private void addEditClientButtonAction(EditFrame editclientFrame) {
        editclientFrame.getChangeEntryButton().addActionListener(e -> {
            List<String> fields = new ArrayList<>();
            fields.add(editclientFrame.getNameField().getText());
            fields.add(editclientFrame.getAddressField().getText());
            fields.add(editclientFrame.getEmailField().getText());
            fields.add(editclientFrame.getNumberField().getText());
            int id = getIdFromText(editclientFrame.getEntryBox().getSelectedItem().toString());
            Client c = clientBLL.findClientById(id);

            if (fields.get(0).equals("") == false) {
                c.setName(fields.get(0));
            }
            if (fields.get(1).equals("") == false) {
                c.setAddress(fields.get(1));
            }
            if (fields.get(2).equals("") == false) {
                c.setEmail(fields.get(2));
            }
            if (fields.get(3).equals("") == false) {
                c.setPhoneNumber(fields.get(3));
            }
            clientBLL.editClient(c);
        });

    }

    /** Function to add actionListener for edit button for product
     * @param editProductFrame
     */
    private void addEditProductButtonAction(EditFrame editProductFrame) {
        editProductFrame.getChangeEntryButton().addActionListener(e -> {
            List<String> fields = new ArrayList<>();
            fields.add(editProductFrame.getNameField().getText());
            fields.add(editProductFrame.getAddressField().getText());
            fields.add(editProductFrame.getEmailField().getText());
            fields.add(editProductFrame.getNumberField().getText());
            int id = getIdFromText(editProductFrame.getEntryBox().getSelectedItem().toString());
            Product c = productBLL.findProductById(id);

            if (fields.get(0).equals("") == false) {
                c.setName(fields.get(0));
            }
            if (fields.get(1).equals("") == false) {
                try {
                    c.setPrice(Float.parseFloat(fields.get(1)));
                } catch (NumberFormatException f) {
                    f.printStackTrace();
                }
            }
            if (fields.get(2).equals("") == false) {
                c.setDescription(fields.get(2));
            }
            if (fields.get(3).equals("") == false) {
                c.setLeftInStock(Integer.parseInt(fields.get(3)));
            }
            productBLL.editProduct(c);
        });

    }

    /**  Function to add actionListener for delete button and view int the clientFrame
     * @param clientFrame the frame to add actionListeners to
     */
    private void addDeleteAndViewClient(ClientFrame clientFrame) {
        clientFrame.getViewAllButton().addActionListener(e -> {
            JFrame tableFrame = new JFrame();
            JTable table = clientBLL.getTableOfClients();
            JScrollPane panel = new JScrollPane(table);
            tableFrame.setVisible(true);
            panel.setVisible(true);
            tableFrame.setSize(new Dimension(700,350));
            table.setFillsViewportHeight(true);
            tableFrame.setContentPane(panel);
        });
        clientFrame.getDeleteClientButton().addActionListener(e -> {
            DeleteFrame deleteFrame = new DeleteFrame("Delete Clients", clientBLL.getClientItems());
            deleteFrame.getDeleteButton().addActionListener(f -> {
                int idCl = getIdFromText(deleteFrame.getComboBox().getSelectedItem().toString());
                Client temp = new Client();
                temp.setIdClient(idCl);
                clientBLL.deleteClient(temp);
            });
        });
    }

    /**Function to add actionListener for delete button and view int the productFrame
     * @param productFrame
     */
    private void addDeleteAndViewProduct(ProductFrame productFrame) {
        productFrame.getViewAllButton().addActionListener(f -> {
            JFrame tableFrame = new JFrame();
            JTable table = productBLL.getTableOfProducts();
            JScrollPane panel = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            tableFrame.setVisible(true);
            panel.setVisible(true);
            tableFrame.setSize(new Dimension(700,350));
            tableFrame.setContentPane(panel);
        });
        productFrame.getDeleteProductButton().addActionListener(e -> {
            DeleteFrame deleteFrame = new DeleteFrame("Delete Products", productBLL.getProductItems());
            deleteFrame.getDeleteButton().addActionListener(f -> {
                int idProd = getIdFromText(deleteFrame.getComboBox().getSelectedItem().toString());
                Product temp = new Product();
                temp.setProductID(idProd);
                productBLL.deleteProduct(temp);
            });
        });
    }

    /** Function to retrieve the id from a string
     * @param s the string to find the id in
     * @return the id from the JComboBox
     */
    private int getIdFromText(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        if (m.find())
            return Integer.parseInt(m.group());
        else
            return -1;
    }

    /**
     * Function to add action listener for order button
     * The order is performed and the bill is created
     * @param orderFrame
     */
    private void addOrderAction(OrderFrame orderFrame) {

        orderFrame.getPlaceOrderButton().addActionListener(e -> {
            int idProd = getIdFromText(orderFrame.getProductBox().getSelectedItem().toString());
            int idClient = getIdFromText(orderFrame.getSelectClientBox().getSelectedItem().toString());
            int quantity = Integer.parseInt(orderFrame.getQuantityField().getText());
            if (quantity > 0) {
                Product product = productBLL.findProductById(idProd);
                int leftInStock = product.getLeftInStock() - quantity;
                if (leftInStock > 0) {
                    float totalPrice = product.getPrice() * quantity;
                    Order order = new Order(totalPrice, idClient, idProd, quantity);
                    product.setLeftInStock(leftInStock);
                    productBLL.editProduct(product);
                    orderBLL.addNewOrder(order);
                    receipt.printReceipt(order,clientBLL,productBLL);
                    orderFrame.getQuantityField().setText("");
                    JOptionPane.showMessageDialog(null, "SUCCESS.", "", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "Stock unavailable.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else
                JOptionPane.showMessageDialog(null, "Quantity not valid.", "ERROR", JOptionPane.ERROR_MESSAGE);

        });
    }

    public static void main(String[] args){

        Controller controller = new Controller();
    }
}
