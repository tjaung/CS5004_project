package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import gameelements.IRoomElement;
import gameelements.Item;
import model.GameModel;
import view.GameView;
import view.PopUp;

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
    String action = e.getActionCommand();
    System.out.println(action);
    switch (action) {
      case "N":
      case "S":
      case "E":
      case "W": {
        try {
          this.model.move(action);
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
      case "X":
        IRoomElement choice = PopUp.openListPopUp(this.model.getRoomModel().getCurrentRoom().getElements());
        PopUp.openDescPopUp(choice);
        break;

      case "T":
        try {
          IRoomElement takeItem = PopUp.openListPopUp(this.model.getRoomModel().getCurrentRoom().getItems());
          this.model.takeItem((Item) takeItem);
          PopUp.confirmPopUp("You have added " + takeItem.getName() + " to your inventory.");
        } catch (Exception error) {
          PopUp.confirmPopUp(error.getMessage());
        }

      case "Q":

//
//        String picPath = this.model.getRoomModel().getCurrentRoom().getPicture();
//        String imgPath = "../AlignQuest/resources/resources/images/" + picPath;
//        ImageIcon imgIcon = new ImageIcon(imgPath);
//        JOptionPane.showConfirmDialog(
//                null,
//                "Examine button",
//                  "OK",
//                  JOptionPane.DEFAULT_OPTION,
//                JOptionPane.QUESTION_MESSAGE,
//                  imgIcon);
//        break;

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
