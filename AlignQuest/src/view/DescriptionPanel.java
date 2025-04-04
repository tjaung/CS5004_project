package view;

import java.awt.*;

import javax.swing.*;

public class DescriptionPanel extends JPanel {
  private JLabel title;
  private JLabel descText;

  public DescriptionPanel() {
    super();
    this.setLayout(new GridLayout(2,0));
    this.title = new JLabel("Description: ");
    this.descText = new JLabel("");
    this.title.setBounds(10,10, 0, 0);
    this.add(this.title);
    this.add(this.descText);


  }
}
