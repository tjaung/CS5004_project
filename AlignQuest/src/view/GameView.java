package view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.VisualController;

public class GameView extends JFrame {
  private JButton x;

  public GameView(String caption) {
    super(caption);
    setSize(200, 125);
    setLocation(200,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    setLayout(new GridLayout(0,2));

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
  }
}
