package view;

import static view.CreateButton.createButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel {

  private JButton inspect;
  private JButton use;
  private JButton drop;
  private JPanel inventoryPanel;

  public InventoryPanel() {
    this.inspect = createButton(null, "Inspect", "I");
    this.use = createButton(null, "Use", "U");
    this.drop = createButton(null, "Drop", "D");
    this.inventoryPanel = new JPanel();
    createPanel();
  }

  public void createPanel() {
    JLabel inventoryLabel = new JLabel("Inventory");
    inventoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
    inventoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.inventoryPanel.setLayout(new BoxLayout(this.inventoryPanel, BoxLayout.Y_AXIS));
    inspect.setAlignmentX(Component.CENTER_ALIGNMENT);
    use.setAlignmentX(Component.CENTER_ALIGNMENT);
    drop.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.inventoryPanel.add(inventoryLabel);
    this.inventoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    this.inventoryPanel.add(inspect);
    this.inventoryPanel.add(use);
    this.inventoryPanel.add(drop);
    this.add(this.inventoryPanel);
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

  public JPanel getInventoryPanel() {
    return this.inventoryPanel;
  }
}
