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


  public GameModel(Readable source) {
    this.roomModel = new RoomModel(source);
    this.player = new Player();
  }

  public void addAnswer() {}

  public boolean validTarget() {
    return true;
  }

  // should Model directly have methods for element interaction or should it call
  // the elements who have methods to interact with each other




}
