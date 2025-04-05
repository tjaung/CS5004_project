package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DescriptionPanel extends JPanel{
    private JPanel descriptionPanel;
    private JLabel descriptionText;

    public DescriptionPanel() {
        super();
        JLabel l = new JLabel("Description");
        this.descriptionPanel = new JPanel();
        this.descriptionPanel.add(l);
        
    }

    public void setDescription() throws Exception{
        try {
            this.descriptionPanel.removeAll();
            this.descriptionText = new JLabel();
            this.descriptionPanel.add(this.descriptionText);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JPanel returnDesPanel() {
        return this.descriptionPanel;
    }

}
