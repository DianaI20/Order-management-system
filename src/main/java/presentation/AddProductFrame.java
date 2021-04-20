package presentation;

import javax.swing.*;
import java.awt.*;

public class AddProductFrame extends JFrame{

    private JTextField nameField;
    private JTextField priceField;
    private JTextField descriptionField;
    private JButton addProductButton;
    private JPanel mainPanel;
    private JTextField quantityField;

    public AddProductFrame(String title) throws HeadlessException {

        super(title);
        this.setVisible(true);
        this.add(mainPanel);
        this.setSize(new Dimension(350, 350));
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

}
