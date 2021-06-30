package presentation;

import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame {
    JPanel buttonPanel;
    JPanel tablePanel;
    JButton addClientButton;
    JButton deleteClientButton;
    JButton viewClientButton;
    JButton editClientButton;
    JTextField idField;
    JTextField nameField;
    JTextField addressField;
    JTextField phoneNumber;
    JTextField mailField;
    JTable table;

    public JTextField getMailField() {
        return mailField;
    }

    public ClientFrame(String title ) throws HeadlessException {
        super(title);

        JLabel name = new JLabel("Name: ");
        JLabel id = new JLabel("Id: ");
        JLabel phoneNumberLb = new JLabel("Phone number:");
        JLabel addrLabel = new JLabel("Address:");
        JLabel mailLabel = new JLabel("Email:");
        addClientButton = new JButton("Add client");
        addClientButton.setPreferredSize(new Dimension(120,25));
        addClientButton.setMaximumSize(new Dimension(120,25));
        viewClientButton = new JButton("View clients");
        viewClientButton.setPreferredSize(new Dimension(120,25));
        viewClientButton.setMaximumSize(new Dimension(120,25));

        deleteClientButton = new JButton("Delete client");
        deleteClientButton.setPreferredSize(new Dimension(120,25));
        deleteClientButton.setMaximumSize(new Dimension(120,25));

        editClientButton = new JButton("Edit client");
        editClientButton.setPreferredSize(new Dimension(120,25));
        editClientButton.setMaximumSize(new Dimension(120,25));


        nameField = new JTextField();
        idField = new JTextField();
        addressField = new JTextField();
        phoneNumber = new JTextField();
        mailField = new JTextField();
        table = new JTable();

        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setPreferredSize(new Dimension(350,400));
        buttonPanel.add(id);
        buttonPanel.add(idField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(name);
        buttonPanel.add(nameField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(addrLabel);
        buttonPanel.add(addressField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(phoneNumberLb);
        buttonPanel.add(phoneNumber);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(mailLabel);
        buttonPanel.add(mailField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(addClientButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(deleteClientButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(viewClientButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(editClientButton);


        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(tablePanel,BorderLayout.EAST);

        buttonPanel.add(Box.createRigidArea(new Dimension(10, 400)));

        this.setPreferredSize(new Dimension(892,477));
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        
   }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getViewClientButton() {
        return viewClientButton;
    }

    public JButton getEditClientButton() {
        return editClientButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getPhoneNumber() {
        return phoneNumber;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }
}