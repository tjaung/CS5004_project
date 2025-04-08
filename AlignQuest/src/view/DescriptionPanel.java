package view;

import java.awt.*;

import javax.swing.*;

public class DescriptionPanel extends JPanel{
    private JPanel descriptionPanel;
    private JLabel descriptionText;
    private int width = 200;
    private int height = 200;

    public DescriptionPanel() {
        super();
        JLabel l = new JLabel("Description");
        JPanel descriptionPanel = new JPanel();
        this.descriptionPanel = descriptionPanel;
        this.descriptionPanel = new JPanel();
        this.descriptionPanel.add(l);
        this.add(this.descriptionPanel);
    }

    public void setDescription(String description) throws Exception{
        try {
            this.descriptionPanel.removeAll();
            this.descriptionText = new JLabel(description);
            //this.descriptionText.setHorizontalAlignment(SwingConstants.CENTER);

            //this.descriptionText.setPreferredSize(new Dimension(this.width, this.height));
            //JPanel panel = new JPanel(new BorderLayout());
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
