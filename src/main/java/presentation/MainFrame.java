package presentation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private JButton clientOperationsButton;
    private JButton productOperationsButton;
    private JButton ordersButton;
    private JPanel mainPanel;

    public MainFrame(String title) throws HeadlessException {

        super(title);
        this.setSize(new Dimension(300,260));
        this.setResizable(false);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }

    public JButton getClientOperationsButton() {
        return clientOperationsButton;
    }

    public JButton getProductOperationsButton() {
        return productOperationsButton;
    }

    public JButton getOrdersButton() {
        return ordersButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
