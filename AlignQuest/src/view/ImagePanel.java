package view;

import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel {

  private JPanel imgPanel;
  private JLabel img;

  public ImagePanel() {
    super();
    JPanel imgPanel = new JPanel();
//    imgPanel.setBackground(Color.blue);
    this.imgPanel = imgPanel;
    this.img = new JLabel();
    this.add(this.imgPanel);
  }

  public void setImage(String path) throws Exception {
    // try to read image from path
    String imgPath = "../AlignQuest/resources/resources/images/" + path;
    try {
      JLabel label = new JLabel();
      label.setIcon(new ImageIcon(imgPath));
      this.imgPanel.removeAll();
      this.imgPanel.add(label);
      // force refresh on img panel
      this.imgPanel.revalidate();
      this.imgPanel.repaint();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public JPanel returnImagePanel() {
    return this.imgPanel;
  }
}
