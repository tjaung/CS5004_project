package view;

import static javax.swing.BoxLayout.*;
import static view.CreateButton.createButton;

import gameelements.IRoomElement;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InventoryPanel extends JPanel {

  private JButton inspect;
  private JButton use;
  private JButton drop;
  private JPanel subPanel;
  private List<IRoomElement> item;

  public InventoryPanel() {
    super();
    this.inspect = createButton(null, "Inspect", "I");
    this.use = createButton(null, "Use", "U");
    this.drop = createButton(null, "Drop", "D");
    this.subPanel = new JPanel();
    this.item = new ArrayList<>();
    createPanel();
  }

  public void createPanel() {
    JLabel inventoryLabel = new JLabel("Inventory");
//    inventoryLabel.setFont(new Font("Arial", Font, 16));
    inventoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.setLayout(new BoxLayout(this, Y_AXIS));
    this.setPreferredSize(new Dimension(10, 10));
    inspect.setAlignmentX(Component.CENTER_ALIGNMENT);
    use.setAlignmentX(Component.CENTER_ALIGNMENT);
    drop.setAlignmentX(Component.CENTER_ALIGNMENT);
    subPanel.setLayout(new BoxLayout(subPanel, Y_AXIS));
    subPanel.setPreferredSize(new Dimension(10, 10));
    JScrollPane scrollPane = new JScrollPane(subPanel);
    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    scrollPane.setMaximumSize(new Dimension(200, 275));
    this.add(inventoryLabel);
    this.add(Box.createRigidArea(new Dimension(10, 10)));
    // add subpanel for buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, X_AXIS));
    buttonPanel.setPreferredSize(new Dimension(10, 10));
    buttonPanel.add(inspect);
    buttonPanel.add(use);
    buttonPanel.add(drop);
    this.add(buttonPanel);
//    this.add(use);
//    this.add(drop);
    this.add(Box.createRigidArea(new Dimension(10, 10)));
    this.add(scrollPane);
  }


  public JButton getInspect() {
    return inspect;
  }

  public JButton getUse() {
    return use;
  }

  public JButton getDrop() {
    return drop;
  }

  public JPanel getSubPanel() {
    return subPanel;
  }



  private void clearPanel(JPanel subPanel){
    for (Component component : subPanel.getComponents()) {
      if (component instanceof JLabel) {
        ((JLabel) component).setText("");
      }
    }
    subPanel.revalidate();
    subPanel.repaint();
  }

  public void updatePanel(List<IRoomElement> items) {
    item = setItem(items);
    item = this.getItem();
    clearPanel(subPanel);
    subPanel.removeAll();
    subPanel.setLayout(new BoxLayout(subPanel, Y_AXIS));
    subPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    for (IRoomElement item : items) {
      JLabel itemLabel = new JLabel(item.getName());
      itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//      itemLabel.setFont(new Font("Arial", Font.BOLD, 15));
      subPanel.add(itemLabel);
    }
    subPanel.revalidate();
    subPanel.repaint();
  }




  public List<IRoomElement> getItem() {
    return item;
  }

  public List<IRoomElement> setItem(List<IRoomElement> item) {
    this.item = item;
    return item;
  }
}
