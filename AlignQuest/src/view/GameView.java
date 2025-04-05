package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.VisualController;

import static view.CreateButton.createButton;


// REFACTOR buildDirection to a method that builds a button based on the string name and includes functionality
// CALL this method 4 times

public class GameView extends JFrame {
  private JButton north, south, east, west;
  // private JButton inventory;
  private JButton lookButton;
  private JButton examineButton;
  private JButton useButton;
  private JButton takeButton;
  private JButton dropButton;
  private JButton answerButton;

  private ImagePanel imagePanel;
  private OptionPanel optionPanel;
  private JPanel descriptionPanel;
  private JPanel inventoryPanel;

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

  }

  public JMenuBar buildMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem exit = new JMenuItem("Exit");

    exit.addActionListener((event) -> System.exit(0));

    menu.add(save);
    menu.add(load);
    menu.add(exit);

    menuBar.add(menu);


    return menuBar;
  }





  public void setEventHandler(VisualController controller) {
    this.setActionListener((ActionListener) controller);
  }

  public void setActionListener(ActionListener controller) {
    this.north.addActionListener(controller);
    this.south.addActionListener(controller);
    this.east.addActionListener(controller);
    this.west.addActionListener(controller);
    this.examineButton.addActionListener(controller);
    this.takeButton.addActionListener(controller);
  }

  public void display() {
    this.setVisible(true);
    //this.imagePanel.setVisible(true);
    //this.optionPanel.setVisible(true);
  }

  public ImagePanel getImagePanel() {
    return this.imagePanel;
  }

  public void updateDesc(String newDesc) {
    // update the this.description panel string
  }


}
