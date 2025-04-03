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

public class InventoryPanel extends JPanel { // Fix: 'extend' should be 'extends'

  // Buttons for the panel
  private JButton inspect;
  private JButton use;
  private JButton drop;
  private JPanel inventoryPanel;

  public InventoryPanel() {
    // Initialize buttons using the createButton method
    this.inspect = createButton("null", "Inspect", "I");
    this.use = createButton("null", "Use", "U");
    this.drop = createButton("null", "Drop", "D");
    this.inventoryPanel = new JPanel();
    // Create and configure the panel
    createPanel();  // Call createPanel to initialize the layout
  }

  // Create the panel with layout and components
  public void createPanel() {
    // Create the label for "Inventory"
    JLabel inventoryLabel = new JLabel("Inventory");
    inventoryLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font for label
    inventoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

    // Center the buttons inside the panel
    inspect.setAlignmentX(Component.CENTER_ALIGNMENT);
    use.setAlignmentX(Component.CENTER_ALIGNMENT);
    drop.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Add components to the panel
    this.inventoryPanel.add(inventoryLabel);  // Add the "Inventory" label
    this.inventoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Add space between label and buttons
    this.inventoryPanel.add(inspect);  // Add buttons below the label
    this.inventoryPanel.add(use);
    this.inventoryPanel.add(drop);

    // Add the inventoryPanel to the current panel (this InventoryPanel instance)
    this.add(this.inventoryPanel);
  }

  public JPanel getInventoryPanel() {
    return this.inventoryPanel;
  }
}
