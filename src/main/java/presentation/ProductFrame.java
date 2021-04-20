package presentation;

import javax.swing.*;
import java.awt.*;

public class ProductFrame extends JFrame{


    private JPanel mainPanel;
    private JButton viewAllButton;
    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;

    public ProductFrame(String title) throws HeadlessException {
        super(title);
        this.setVisible(true);
        this.add(mainPanel);
        this.setSize(new Dimension(310,330));

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JButton getViewAllButton() {
        return viewAllButton;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public JButton getEditProductButton() {
        return editProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

}
