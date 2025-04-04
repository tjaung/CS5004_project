package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class descriptionPanel extends JPanel{
    private JPanel descriptionPanel;
    private JLabel descriptionText;

    public descriptionPanel(String description) {
        super();
        JLabel l = new JLabel("Description");
        this.descriptionPanel.add(l);
        
    }

    public void setDescription(String description) throws Exception{
        try {
            this.descriptionPanel.removeAll();
            this.descriptionText = new JLabel(description);
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
