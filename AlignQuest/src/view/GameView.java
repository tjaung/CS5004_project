package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.VisualController;

public class GameView extends JFrame {
  private JButton north, south, east, west;
  private JButton inventory;
  private JButton look;
  private JButton examine;
  private JButton use;
  private JButton take;
  private JButton drop;
  private JButton answer;
  private JPanel imagePanel;
  private JPanel optionPanel;
  private JPanel movePanel;
  private JPanel actionPanel;

  public GameView(String caption) {
    // setup frame
    super(caption);

    setSize(400, 400);
    setLocation(400,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Setup panels
    this.imagePanel = new JPanel();
    this.imagePanel.setBounds(0,0,400,200);
    this.optionPanel = new JPanel();
    this.optionPanel.setBounds(0,200,400,200);
    this.movePanel = new JPanel();
    this.movePanel.setBounds(0,0,200,200);
    this.actionPanel = new JPanel();
    this.actionPanel.setBounds(200,0,200,200);

    this.setLayout(new GridLayout(2, 0));
    this.add(this.imagePanel);
    this.add(this.optionPanel);

    // add butttons
    this.north = new JButton("");
    try {
      ImageIcon northIcon = new ImageIcon("../AlignQuest/resources/resources/images/north.png");
      this.north.setIcon(northIcon);
      this.north.setBorderPainted(false);
      this.north.setContentAreaFilled(false);
      this.north.setFocusPainted(false);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
    this.north.setBounds(70, 10, 80, 80);
    this.south = new JButton("");
    this.south.setBounds(70, 70, 50, 50);
    this.east = new JButton("");
    this.east.setBounds(130, 35, 50, 50);
    this.west = new JButton("");
    this.west.setBounds(10, 35, 50, 50);

    /*this.optionPanel.setLayout(new GridLayout(0,2));
    this.optionPanel.add(this.movePanel);
    this.optionPanel.add(this.actionPanel);*/

    /*this.movePanel.setLayout(new GridLayout(2,2));
    this.actionPanel.setLayout(new GridLayout(2, 4));*/

    this.optionPanel.setLayout(null);
    this.optionPanel.add(this.north);
    this.optionPanel.add(this.south);
    this.optionPanel.add(this.east);
    this.optionPanel.add(this.west);

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
