package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.VisualController;


// REFACTOR buildDirection to a method that builds a button based on the string name and includes functionality
// CALL this method 4 times

public class GameView extends JFrame {
  private JButton north, south, east, west;
  private JButton inventory;
  private JButton look;
  private JButton examine;
  private JButton use;
  private JButton take;
  private JButton drop;
  private JButton answer;
  private JPanel imgPanel;
  private JPanel viewPanel;
  private JPanel optionPanel;
  private JPanel descriptionPanel;
  private JPanel inventoryPanel;


  public GameView(String caption) {
    // setup frame
    super(caption);

    setSize(800, 800);
    setLocation(100,100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(2,2));

    // build image panel
    this.buildImagePanel();
    // build panels
    this.buildPanels();
    // build direction buttons
    this.buildDirections();

    // build menu bar
    this.setJMenuBar(this.buildMenu());
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

  public void buildPanels() {
    this.viewPanel = new JPanel();
    this.viewPanel.setBounds(0,0,800,500);
    this.optionPanel = new JPanel();
    this.optionPanel.setBounds(0,500,800,300);

    this.add(this.viewPanel);
    this.add(this.optionPanel);
  }

  public void buildImagePanel() {
    JPanel imgPanel = new JPanel();
//    imgPanel.setBounds(0,0,800,500);
    imgPanel.setBackground(Color.blue);
    this.imgPanel = imgPanel;
    this.add(this.imgPanel);
  }

  public void setImgPanelImage(String path) throws Exception {
    // try to read image from path
    String imgPath = "./AlignQuest/resources/resources/images/" + path;
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

  public void buildDirections() {
    this.north = new JButton("");
    this.south = new JButton("");
    this.east = new JButton("");
    this.west = new JButton("");

    try {
      ImageIcon northIcon = new ImageIcon("./AlignQuest/resources/resources/images/north.png");
      this.north.setIcon(northIcon);
      this.north.setBorderPainted(false);
      this.north.setContentAreaFilled(false);
      this.north.setFocusPainted(false);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      ImageIcon southIcon = new ImageIcon("./AlignQuest/resources/resources/images/south.png");
      this.south.setIcon(southIcon);
      this.south.setBorderPainted(false);
      this.south.setContentAreaFilled(false);
      this.south.setFocusPainted(false);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      ImageIcon eastIcon = new ImageIcon("./AlignQuest/resources/resources/images/east.png");
      this.east.setIcon(eastIcon);
      this.east.setBorderPainted(false);
      this.east.setContentAreaFilled(false);
      this.east.setFocusPainted(false);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      ImageIcon westIcon = new ImageIcon("./AlignQuest/resources/resources/images/west.png");
      this.west.setIcon(westIcon);
      this.west.setBorderPainted(false);
      this.west.setContentAreaFilled(false);
      this.west.setFocusPainted(false);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }

    this.north.setBounds(70, 20, 50, 50);
    this.south.setBounds(70, 110, 50, 50);
    this.east.setBounds(130, 65, 50, 50);
    this.west.setBounds(10, 65, 50, 50);

    this.optionPanel.setLayout(null);
    this.optionPanel.add(this.north);
    this.optionPanel.add(this.south);
    this.optionPanel.add(this.east);
    this.optionPanel.add(this.west);
  }

  public void setEventHandler(VisualController visualController) {
    this.setActionListener((ActionListener) visualController);
  }

  public void setActionListener(ActionListener controller) {

  }

  public void display() {
    this.setVisible(true);
    //this.imagePanel.setVisible(true);
    //this.optionPanel.setVisible(true);
  }
}
