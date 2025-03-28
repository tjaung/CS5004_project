package model;

import gameelements.Inventory;
import gameelements.Player;

/**
 * Model in MVC schema. Has a database and handles logic.
 */
public class GameModel {
  RoomModel roomModel; // database
  Player player;
  // inventory is contained within player


  public GameModel(String gameFileName) throws Exception {
    this.roomModel = new RoomModel(gameFileName);
    this.player = new Player();
  }

  public Player getPlayer() {
    return player;
  }

  public RoomModel getRoomModel() {
    return roomModel;
  }

  public void move(String s) {
    switch (s.toUpperCase()) {
      case "N":
        if (this.getRoomModel().getCurrentRoom().getN() > 0) {
          this.getPlayer().setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getN()));
          this.roomModel.setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getN()));
        }
        else if (this.getRoomModel().getCurrentRoom().getN() == 0) {
          throw new IllegalArgumentException("There is no room to the north\n");
        }
        else {
          // get specific of why path is blocked
          throw new IllegalArgumentException("The path is blocked\n");
        }
        break;
      case "S":
        if (this.getRoomModel().getCurrentRoom().getS() > 0) {
          this.getPlayer().setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getS()));
          this.roomModel.setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getS()));
        }
        else if (this.getRoomModel().getCurrentRoom().getS() == 0) {
          throw new IllegalArgumentException("There is no room to the south\n");
        }
        else {
          // get specific of why path is blocked
          throw new IllegalArgumentException("The path is blocked\n");
        }
        break;
      case "E":
        if (this.getRoomModel().getCurrentRoom().getE() > 0) {
          this.getPlayer().setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getE()));
          this.roomModel.setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getE()));
        }
        else if (this.getRoomModel().getCurrentRoom().getE() == 0) {
          throw new IllegalArgumentException("There is no room to the east\n");
        }
        else {
          // get specific of why path is blocked
          throw new IllegalArgumentException("The path is blocked\n");
        }
        break;
      case "W":
        if (this.getRoomModel().getCurrentRoom().getW() > 0) {
          this.getPlayer().setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getW()));
          this.roomModel.setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getW()));
        }
        else if (this.getRoomModel().getCurrentRoom().getW() == 0) {
          throw new IllegalArgumentException("There is no room to the west\n");
        }
        else {
          // get specific of why path is blocked
          throw new IllegalArgumentException("The path is blocked\n");
        }
        break;
    }
  }

  public void answer() {}

  public boolean validTarget() {
    return true;
  }

  // should Model directly have methods for element interaction or should it call
  // the elements who have methods to interact with each other




}
