package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class OrderFrame extends JFrame{
    private JComboBox selectClientBox;
    private JPanel mainPanel;
    private JComboBox productBox;
    private JTextField quantityField;
    private JButton placeOrderButton;

    public OrderFrame(String title, List<String> products, List<String> clients ) throws HeadlessException {
        super(title);
        this.setVisible(true);
        this.add(mainPanel);
        this.setSize(new Dimension(310,330));
        for(String s : products){
            productBox.addItem(s);
        }
        for(String s : clients){
            selectClientBox.addItem(s);
        }
    }

    public JComboBox getSelectClientBox() {
        return selectClientBox;
    }

    public JButton getPlaceOrderButton() {
        return placeOrderButton;
    }

    public JComboBox getProductBox() {
        return productBox;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }
}
