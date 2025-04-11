package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.VisualController;

import static view.CreateButton.createButton;

public class GameView extends JFrame {
  private JButton north, south, east, west;
  // private JButton inventory;
  private JButton lookButton;
  private JButton examineButton;
  private JButton useButton;
  private JButton takeButton;
  private JButton dropButton;
  private JButton answerButton;
  private JMenuItem exit;
  private JMenuItem save;
  private JMenuItem load;
  private JButton inspectButton;
  private ImagePanel imagePanel;
  private OptionPanel optionPanel;
  private DescriptionPanel descriptionPanel;
  private InventoryPanel inventoryPanel;

  public GameView(String caption) {
    // setup frame
    super(caption);

    setSize(800, 800);
    setLocation(100,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(2,2));

    // build panels
    this.imagePanel = new ImagePanel();
    this.optionPanel = new OptionPanel();
    this.descriptionPanel = new DescriptionPanel();
    this.inventoryPanel = new InventoryPanel();
    this.add(this.imagePanel);
    this.add(this.optionPanel);
    this.add(this.descriptionPanel);
    this.add(this.inventoryPanel);


//    this.inventoryPanel = new InventoryPanel();
    // build menu bar
    this.setJMenuBar(this.buildMenu());

    this.north = this.optionPanel.getNorth();
    this.south = this.optionPanel.getSouth();
    this.east = this.optionPanel.getEast();
    this.west = this.optionPanel.getWest();
    this.examineButton = this.optionPanel.getExamine();
    this.takeButton = this.optionPanel.getTakeButton();
    this.answerButton = this.optionPanel.getAnswerButton();
    this.useButton = this.inventoryPanel.getUse();
    this.dropButton = this.inventoryPanel.getDrop();
    this.inspectButton = this.inventoryPanel.getInspect();
  }

  public JMenuBar buildMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem exit = new JMenuItem("Exit");

    this.exit = exit;
    this.save = save;
    this.load = load;
    this.exit.setActionCommand("Q");
    this.save.setActionCommand("+");
    this.load.setActionCommand("-");

    menu.add(save);
    menu.add(load);
    menu.add(exit);

    menuBar.add(menu);

    return menuBar;
  }

  public void setEventHandler(VisualController controller) {
    this.setActionListener((ActionListener) controller);
  }

  // can this be passed on by using frame composition? buttons added to frame, frame sets actionlistener
  // to view which sits its own listener to be the controller
  public void setActionListener(ActionListener controller) {
    this.north.addActionListener(controller);
    this.south.addActionListener(controller);
    this.east.addActionListener(controller);
    this.west.addActionListener(controller);
    this.examineButton.addActionListener(controller);
    this.takeButton.addActionListener(controller);
    this.inspectButton.addActionListener(controller);
    this.answerButton.addActionListener(controller);
    this.useButton.addActionListener(controller);
    this.dropButton.addActionListener(controller);

    this.exit.addActionListener(controller);
    this.save.addActionListener(controller);
    this.load.addActionListener(controller);

  }

  public void display() {
    this.setVisible(true);
  }

  public ImagePanel getImagePanel() {
    return this.imagePanel;
  }

  public DescriptionPanel getDescriptionPanel() { return this.descriptionPanel; }

  public InventoryPanel getInventoryPanel() {
    return this.inventoryPanel;
  }

  public void updateImage(String imgPath) throws Exception {
    try {
      this.imagePanel.setImage(imgPath);
    } catch (Exception e) {
      this.imagePanel.setImage("generic_item.png");
    }
  }

  public void updateDesc(String newDesc, boolean keepPrevious) throws Exception {
    // update the this.description panel string
    if (!keepPrevious) {
      this.descriptionPanel.setDescription(newDesc);
    }
    else if (keepPrevious) {
      this.descriptionPanel.appendDescription(newDesc);
    }
  }
}
