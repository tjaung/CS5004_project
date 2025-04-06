package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
      // Move commands: User presses a move buton, which will send them to the corresponding room.
      // If they are unable to move in a specified direction (room does not exist, or room blocked),
      // They will not move and an error will be displayed. These messages should be in the description
      // panel.
      case "N":
      case "S":
      case "E":
      case "W": {
        try {
          this.model.move(action);
          System.out.println(this.model.getString());

//          this.view.updateDesc(this.model.getString());
          this.view.updateImage(this.model.getRoomModel().getCurrentRoom().getPicture());
        }
        catch (Exception error) {
          System.out.println(error.getMessage());
//          this.view.showError(e);
        }
        break;
      }
      // Examine command: User picks from a list popup of all of the game elements in the currrent room.
      // They can choose one to view, where they will get a desc popup of picture and text desc.
      case "X":
        IRoomElement choice = PopUp.openListPopUp(this.model.getRoomModel().getCurrentRoom().getElements());
        PopUp.openDescPopUp(choice);
        break;
      // Take command: Shows popup of items available to take. User chooses an item and controller calls model
      // to add it to the inventory. If successful, send success message, else catch error and display it.
      case "T":
        try {
          IRoomElement takeItem = PopUp.openListPopUp(this.model.getRoomModel().getCurrentRoom().getItems());
          this.model.takeItem((Item) takeItem);
          PopUp.confirmPopUp("You have added " + takeItem.getName() + " to your inventory.");
        } catch (Exception error) {
          PopUp.confirmPopUp(error.getMessage());
        }
        break;
      // Answer command: Specific to solving riddle puzzles. If there is a riddle puzzle, the user
      // can call this command to get an input popup. The input is sent to the puzzle to see if
      // the answer is correct to unlock the path. If there is no puzzle, display that popup. If
      // it is incorrect, display that. If it is correct, display a success message.
      case "A":
        try {
          String answer = PopUp.inputPopUp("Enter your answer:");
          this.model.answerRiddle(answer);
          PopUp.confirmPopUp("SUCCESS! You solved this puzzle with the answer " + answer);
        } catch (Exception error) {
          PopUp.confirmPopUp(error.getMessage());
        }
        break;

      // Use command: User specifies an item in their inventory that they want to use.
      // This can either be on a monster or a puzzle.
      case "U":
        try {
          // bad casting method from item to IRoomElement back to item :(
          List<IRoomElement> items = this.model.getPlayer().getInventory().getItems().stream()
                  .map(subType -> (IRoomElement) subType)
                  .toList();
          IRoomElement item = PopUp.openListPopUp(items);
          Item useItem = (Item) item;
          this.model.useItem(useItem);
          PopUp.confirmPopUp(useItem.getWhenUsed());
          // update image if we need to
          this.view.updateImage(this.model.getRoomModel().getCurrentRoom().getPicture());
        }
        catch (Exception error) {
          PopUp.confirmPopUp(error.getMessage());
        }
      break;
      case "Q":


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
