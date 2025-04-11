package view;

import java.awt.*;

import javax.swing.*;

public class DescriptionPanel extends JPanel{
    private JPanel descriptionPanel;
    private JTextArea descriptionText;

    private int width = 200;
    private int height = 200;

    public DescriptionPanel() {
        super();
        JLabel l = new JLabel("Description");
        this.descriptionPanel = new JPanel(new BorderLayout());
        this.descriptionPanel.add(l);
        this.add(this.descriptionPanel);
    }

    public void appendDescription(String newDescription) {
        if (newDescription == null) {
            return;
        }
        this.descriptionText.append(newDescription);
    }

    public void setDescription(String description) throws Exception{
        try {
            this.descriptionPanel.removeAll();
            this.descriptionText = new JTextArea(description, 20, 30);
            this.descriptionText.setLineWrap(true);
            this.descriptionText.setWrapStyleWord(true);
            this.descriptionText.setEditable(false); // Prevents user editing
            this.descriptionText.setMargin( new Insets(10,10,10,10) );
            this.descriptionPanel.add(this.descriptionText);
            // force refresh on img panel
            this.descriptionPanel.revalidate();
            this.descriptionPanel.repaint();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JPanel returnDesPanel() {
        return this.descriptionPanel;
    }

}
