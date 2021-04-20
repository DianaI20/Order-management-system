package presentation;

import javax.swing.*;
import java.awt.*;

public class AddClientFrame extends JFrame{

    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField adressField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JButton addClientButton;

    public AddClientFrame(String title) throws HeadlessException {
        super(title);
        this.setVisible(true);
        this.add(mainPanel);
        this.setSize(new Dimension(350, 350));
        mainPanel.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAdressField() {
        return adressField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }
}
