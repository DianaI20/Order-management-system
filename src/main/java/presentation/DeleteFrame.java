package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class DeleteFrame extends JFrame{

    private JPanel mainPanel;
    private JButton deleteButton;
    private JComboBox comboBox;
    public DeleteFrame(String title, List<String> items) throws HeadlessException {
        super(title);

        for(String s : items){
            comboBox.addItem(s);
        }
        this.setVisible(true);
        mainPanel.setVisible(true);
        this.setSize(new Dimension(320, 320));
        this.add(mainPanel);
   }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
