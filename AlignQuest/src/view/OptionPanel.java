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
    buildDirections(this.movePanel);
    buildOptions(this.actionPanel);
    this.add(movePanel);
    this.add(actionPanel);
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

  public void buildOptions(JPanel panel) {
    this.takeButton = createButton(null, "Take", "T");
    this.examineButton = createButton(null, "Examine", "X");
    this.answerButton = createButton(null, "Answer", "A");

    panel.add(this.takeButton);
    panel.add(this.examineButton);
    panel.add(this.answerButton);
  }
}
