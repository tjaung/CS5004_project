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
    switch (e.getActionCommand()) {
      case "N":
      case "S":
      case "E":
      case "W": {
        try {
          this.model.move(e.getActionCommand());
          System.out.println(this.model.getString());

//          this.view.updateDesc(this.model.getString());
//          this.view.updateImg();
        }
        catch (Exception error) {
          System.out.println(error.getMessage());
//          this.view.showError(e);
        }

        break;
      }

    }
  }

  public void go() throws Exception {
    String imgPath = this.model.getRoomModel().getCurrentRoom().getPicture();
    this.view.getImagePanel().setImage(imgPath);
    this.view.display();
    // this.view.updateDesc(this.model.getString());
    // this.view.updateImage(this.model.getImage());
  }
}
