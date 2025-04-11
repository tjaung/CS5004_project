package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import gameelements.IRoomElement;
import gameelements.Item;
import model.GameModel;
import view.GameView;
import view.InventoryPanel;
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
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    System.out.println(action);
    switch (action) {
      case "N":
      case "S":
      case "E":
      case "W": this.movePlayer(action);
        break;
      case "X": this.examine();
        break;
      case "T": this.takeItem();
        break;
      case "I": this.inspect();
        break;
      case "A": this.answerRiddle();
        break;
      case "D": this.dropItem();
        break;
      case "U": this.useItem();
        break;
      case "Q": this.quitGame();
        break;
      case "+": this.saveGame();
        break;
      case "-": this.loadGame();
        break;
    }
    try {
      this.view.updateDesc(this.model.getEndTurnMessage(), true);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    if (this.model.isGameOver()) {
      PopUp.quitPopUp(this.model.getPlayer().getName(), this.model.getPlayer().getScore(), this.model.endGame());
      System.exit(0);
    }
  }

  public void go() throws Exception {
    String imgPath = this.model.getRoomModel().getCurrentRoom().getPicture();
    this.view.getImagePanel().setImage(imgPath);
    String descriptionPath = this.model.getRoomModel().getCurrentRoom().getDescription();
    this.view.getDescriptionPanel().setDescription(descriptionPath);
    this.view.display();
    this.view.updateDesc(this.model.getString(), false);
    // this.view.updateImage(this.model.getImage());
  }

  // Move commands: User presses a move buton, which will send them to the corresponding room.
  // If they are unable to move in a specified direction (room does not exist, or room blocked),
  // They will not move and an error will be displayed. These messages should be in the description
  // panel.
  private void movePlayer(String action) {
    try {
      this.model.move(action);
      System.out.println(this.model.getString());
      // Update views image and description
      this.view.updateDesc(this.model.getString(), false);
      this.view.updateImage(this.model.getRoomModel().getCurrentRoom().getPicture());
      // process turn or endTurn()
    } catch (Exception error) {
      System.out.println(error.getMessage());
    }
  }

  // Examine command: User picks from a list popup of all the game elements in the currrent room.
  // They can choose one to view, where they will get a desc popup of picture and text desc.
  private void examine() {
    List<IRoomElement> roomElements = this.model.getRoomModel().getCurrentRoom().getElements();
    IRoomElement choice = PopUp.openListPopUp(roomElements);
    if (choice != null) {
      PopUp.openDescPopUp(choice);
    }
  }

  // Take command: Shows popup of items available to take. User chooses an item and controller calls model
  // to add it to the inventory. If successful, send success message, else catch error and display it.
  private void takeItem() {
    try {
      List<IRoomElement> items = this.model.getRoomModel().getCurrentRoom().getItems();
      IRoomElement takeItem = PopUp.openListPopUp(items);
      this.model.takeItem((Item) takeItem);
      // popup confirmation
      PopUp.confirmPopUp("You have added " + takeItem.getName() + " to your inventory.");
      List<IRoomElement> newInv = this.castItemToRoomElement(
              this.model.getPlayer().getInventory().getItems()
      );
      this.view.getInventoryPanel().updatePanel(newInv);
      this.view.updateDesc(this.model.getString(), false);
    } catch (Exception error) {
//      PopUp.confirmPopUp(error.getMessage());
    }
  }

  private void inspect() {
    try {
      List<Item> items = this.model.getPlayer().getInventory().getItems();
      List<IRoomElement> itemsArr = this.castItemToRoomElement(items);
      IRoomElement item = PopUp.openListPopUp(itemsArr);
      PopUp.openDescPopUp(item);
    } catch (Exception error) {
//      PopUp.confirmPopUp(error.getMessage());
    }
  }

  // Answer command: Specific to solving riddle puzzles. If there is a riddle puzzle, the user
  // can call this command to get an input popup. The input is sent to the puzzle to see if
  // the answer is correct to unlock the path. If there is no puzzle, display that popup. If
  // it is incorrect, display that. If it is correct, display a success message.
  private void answerRiddle() {
    try {
      String answer = PopUp.inputPopUp("Enter your answer:");
      if (answer != null) {
        this.model.answerRiddle(answer);
        PopUp.confirmPopUp("SUCCESS! You solved this puzzle with the answer " + answer);
        this.view.updateImage(this.model.getRoomModel().getCurrentRoom().getPicture());
        this.view.updateDesc(this.model.getString(), false);
        //process turn
      }
    } catch (Exception error) {
      PopUp.confirmPopUp(error.getMessage());
    }
  }

  private void dropItem() {
    try {
      List<IRoomElement> items = this.castItemToRoomElement(
              this.model.getPlayer().getInventory().getItems()
      );
      IRoomElement dropItem = PopUp.openListPopUp(items);
      this.model.dropItem((Item) dropItem);

      //Popups
      PopUp.confirmPopUp("You dropped the item " + dropItem.getName());
      // update inv panel with new items
      List<IRoomElement> newInv = this.castItemToRoomElement(
              this.model.getPlayer().getInventory().getItems()
      );
      this.view.getInventoryPanel().updatePanel(newInv);
    } catch (Exception error) {
//      PopUp.confirmPopUp(error.getMessage());
    }
  }

  // Use command: User specifies an item in their inventory that they want to use.
  // This can either be on a monster or a puzzle.
  private void useItem() {
    try {
      List<Item> invItems = this.model.getPlayer().getInventory().getItems();
      List<IRoomElement> items = this.castItemToRoomElement(invItems);
      IRoomElement item = PopUp.openListPopUp(items);
      if(item == null) {
        return;
      }
      Item useItem = (Item) item;

      this.model.useItem(useItem);

      PopUp.confirmPopUp(useItem.getWhenUsed());

      // update image if we need to
      this.view.updateImage(this.model.getRoomModel().getCurrentRoom().getPicture());
      this.view.updateDesc(this.model.getString(), false);
      // process turn
    } catch (Exception error) {
      PopUp.confirmPopUp(error.getMessage());
    }
  }

  private void quitGame() {
    PopUp.quitPopUp(this.model.getPlayer().getName(), this.model.getPlayer().getScore(), "");
    System.exit(0);
  }

  private void saveGame() {
    try {
      String name = PopUp.inputPopUp("Save file name:");
      String message = this.model.saveGame(name);
      PopUp.confirmPopUp(message);
    } catch (Exception error) {
      PopUp.confirmPopUp(error.getMessage());
    }
  }

  private void loadGame() {
    try {
      File saveDirectory = new File("../AlignQuest/saves/");
      File[] saveFiles = saveDirectory.listFiles();
      List<String> saves = new ArrayList<>();
      for (File file : saveFiles) {
        if (!file.getName().contains(".gitignore")) {
          saves.add(file.getName());
        }
      }
      String selection = PopUp.openSaveList(saves);
    } catch (Exception error) {
      PopUp.confirmPopUp(error.getMessage());
    }
  }

  private List<IRoomElement> castItemToRoomElement(List<Item> itemList) {
    List<IRoomElement> roomElements = new ArrayList<IRoomElement>();
    for (Item item : itemList) {
      IRoomElement i = (IRoomElement) item;
      roomElements.add(i);
    }
    return roomElements;
  }

}