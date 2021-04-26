package presentation;

import javax.swing.*;
import java.awt.*;

public class ProductFrame extends JFrame{
    JPanel buttonPanel;
    JPanel tablePanel;
    JButton addProductButton;
    JButton deleteProductButton;
    JButton viewProductButton;
    JButton editProductButton;

    JTextField idField;
    JTextField nameField;
    JTextField priceField;
    JTextField descriptionField;
    JTextField quantityField;

    public ProductFrame (String title, JTable table) throws HeadlessException {
        super(title);

        JLabel name = new JLabel("Name: ");
        JLabel id = new JLabel("Id: ");
        JLabel priceLabel = new JLabel("Price:");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel quantityLabel = new JLabel("Quantity:");

        addProductButton = new JButton("Add product");
        addProductButton.setPreferredSize(new Dimension(120,25));
        addProductButton.setMaximumSize(new Dimension(120,25));

        viewProductButton = new JButton("View product");
        viewProductButton.setPreferredSize(new Dimension(120,25));
        viewProductButton.setMaximumSize(new Dimension(120,25));

        deleteProductButton = new JButton("Delete product");
        deleteProductButton.setPreferredSize(new Dimension(120,25));
        deleteProductButton.setMaximumSize(new Dimension(120,25));

        editProductButton = new JButton("Edit product");
        editProductButton.setPreferredSize(new Dimension(120,25));
        editProductButton.setMaximumSize(new Dimension(120,25));


        nameField = new JTextField();
        idField = new JTextField();
        priceField = new JTextField();
        descriptionField = new JTextField();
        quantityField = new JTextField();




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

        buttonPanel.add(descriptionLabel);
        buttonPanel.add(descriptionField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(priceLabel);
        buttonPanel.add(priceField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(quantityLabel);
        buttonPanel.add(quantityField);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        buttonPanel.add(addProductButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(deleteProductButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(viewProductButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonPanel.add(editProductButton);

        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(buttonPanel, BorderLayout.WEST);
        getContentPane().add(tablePanel,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(997,466));
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void setTablePanel(JPanel tablePanel) {
        this.tablePanel = tablePanel;
    }

    public JButton getAddProductButton() {
        return addProductButton;
    }

    public void setAddProductButton(JButton addProductButton) {
        this.addProductButton = addProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public void setDeleteProductButton(JButton deleteProductButton) {
        this.deleteProductButton = deleteProductButton;
    }

    public JButton getViewProductButton() {
        return viewProductButton;
    }

    public void setViewProductButton(JButton viewProductButton) {
        this.viewProductButton = viewProductButton;
    }

    public JButton getEditProductButton() {
        return editProductButton;
    }

    public void setEditProductButton(JButton editProductButton) {
        this.editProductButton = editProductButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(JTextField descriptionField) {
        this.descriptionField = descriptionField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }
}
