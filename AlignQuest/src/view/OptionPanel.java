package view;

import java.awt.*;

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
    this.movePanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    this.buildDirections(this.movePanel, c);
    this.buildActions(this.actionPanel);
  }

  public JPanel getMovePanel() {
    return this.movePanel;
  }

  public void addButton() {

  }

  public void buildDirections(JPanel panel, GridBagConstraints c) {
    this.north = createButton("../AlignQuest/resources/resources/images/north.png", "", "N");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    panel.add(this.north, c);

    this.south = createButton("../AlignQuest/resources/resources/images/south.png", "", "S");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 1;
    c.gridy = 2;
    panel.add(this.south, c);

    this.east = createButton("../AlignQuest/resources/resources/images/east.png", "", "E");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 2;
    c.gridy = 1;
    panel.add(this.east, c);

    this.west = createButton("../AlignQuest/resources/resources/images/west.png", "", "W");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 0;
    c.gridy = 1;
    panel.add(this.west, c);
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

  public JButton getAnswerButton() {
    return this.answerButton;
  }


}
