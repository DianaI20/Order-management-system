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
    private JButton viewAllOrdersButton;
    private JButton viewClientsButton;
    private JButton viewProductsButton;
    private JPanel tablePanel;

    public OrderFrame(String title, List<String> products, List<String> clients ) throws HeadlessException {
        super(title);
        this.setVisible(true);
        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(mainPanel,BorderLayout.WEST);
        this.add(tablePanel,BorderLayout.EAST);
      //  this.setSize(new Dimension(310,330));
        for(String s : products){
            productBox.addItem(s);
        }
        for(String s : clients){
            selectClientBox.addItem(s);
        }
        this.setSize(900,448);
        this.setResizable(false);
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

    public JButton getViewAllOrdersButton() {
        return viewAllOrdersButton;
    }

    public JButton getViewClientsButton() {
        return viewClientsButton;
    }

    public JButton getViewProductsButton() {
        return viewProductsButton;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}




