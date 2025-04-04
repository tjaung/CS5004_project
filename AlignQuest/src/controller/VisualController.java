package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.GameModel;
import view.GameView;

public class VisualController implements ActionListener {
  GameModel model;
  GameView view;

  public VisualController(GameModel model, GameView view) {
    this.model = model;
    this.view = view;
    this.view.setEventHandler(this);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "N":
      case "S":
      case "W":
      case "E":
      try {
        this.model.move(command);
        // update model string
//        this.model.setString(command);
        // display model string
        System.out.println(this.model.toString());
        break;
      } catch (Exception e1) {
        System.out.println(e1.getMessage());
      }


    }
  }

  public void go() throws Exception {
    String imgPath = this.model.getRoomModel().getCurrentRoom().getPicture();
    this.view.getImagePanel().setImage(imgPath);
    this.view.display();
  }
}
