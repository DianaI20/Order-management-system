package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditFrame extends JFrame{
    private JComboBox entryBox;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField numberField;
    private JButton changeEntryButton;
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel secondFieldLabel;
    private JLabel thirdFieldLabel;
    private JLabel fourthFieldLabel;

    public EditFrame(String title, List<String> items) throws HeadlessException {
        super(title);
        for(String s : items){
            entryBox.addItem(s);
        }
        this.setVisible(true);
        mainPanel.setVisible(true);
        this.setSize(new Dimension(320, 320));
        this.add(mainPanel);
    }
    public EditFrame(String title, List<String> items,String[] labels) throws HeadlessException {
        super(title);
        nameLabel.setText(labels[0]);
        secondFieldLabel.setText(labels[1]);
        thirdFieldLabel.setText(labels[2]);
        fourthFieldLabel.setText(labels[3]);
        for(String s : items){
            entryBox.addItem(s);
        }
        this.setVisible(true);
        mainPanel.setVisible(true);
        this.setSize(new Dimension(320, 320));
        this.add(mainPanel);
    }

    public JComboBox getEntryBox() {
        return entryBox;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getNumberField() {
        return numberField;
    }

    public JButton getChangeEntryButton() {
        return changeEntryButton;
    }
}
