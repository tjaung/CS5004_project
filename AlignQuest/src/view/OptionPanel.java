package view;

import javax.swing.*;
import javax.swing.text.html.Option;

import static view.CreateButton.createButton;

public class OptionPanel extends JPanel {
  private JButton north;
  private JButton south;
  private JButton east;
  private JButton west;

  private JButton takeButton;
  private JButton examineButton;
  private JButton answerButton;

  private JPanel movePanel;
  private JPanel actionPanel;

  public OptionPanel() {
    super();
    this.movePanel = new JPanel();
    this.actionPanel = new JPanel();
    this.add(movePanel);
    this.add(actionPanel);
    this.buildDirections(this.movePanel);
    this.buildActions(this.actionPanel);
  }

  public JPanel getMovePanel() {
    return this.movePanel;
  }

  public void addButton() {

  }

  public void buildDirections(JPanel panel) {
    this.north = createButton("../AlignQuest/resources/resources/images/north.png", "", "N");
    this.south = createButton("../AlignQuest/resources/resources/images/south.png", "", "S");
    this.east = createButton("../AlignQuest/resources/resources/images/east.png", "", "E");
    this.west = createButton("../AlignQuest/resources/resources/images/west.png", "", "W");

    panel.add(this.north);
    panel.add(this.south);
    panel.add(this.east);
    panel.add(this.west);
  }

  public void buildActions(JPanel panel) {
    this.takeButton = createButton(null, "Take", "T");
    this.examineButton = createButton(null, "Examine", "X");
    this.answerButton = createButton(null, "Answer", "A");

    panel.add(this.takeButton);
    panel.add(this.examineButton);
    panel.add(this.answerButton);
  }

  public JButton getNorth() {
    return this.north;
  }

  public JButton getSouth() {
    return this.south;
  }

  public JButton getEast() {
    return this.east;
  }

  public JButton getWest() {
    return this.west;
  }

  public JButton getExamine() {
    return this.examineButton;
  }

  public JButton getTakeButton() {
    return this.takeButton;
  }


}
