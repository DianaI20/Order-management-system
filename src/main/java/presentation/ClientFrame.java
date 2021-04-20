package presentation;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {


    private JPanel mainClientPanel;
    private JButton addClientButton;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewAllButton;

    public ClientFrame(String title) throws HeadlessException {
        super(title);
        this.setVisible(true);
        this.add(mainClientPanel);
        this.setSize(new Dimension(310,330));
        this.setResizable(false);

        mainClientPanel.setVisible(true);
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public JButton getEditClientButton() {
        return editClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getViewAllButton() {
        return viewAllButton;
    }

}
