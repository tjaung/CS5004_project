package model;

import java.util.NoSuchElementException;

import gameelements.Item;
import gameelements.Player;
import gameelements.Room;

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
      case "S":
        if (this.getRoomModel().getCurrentRoom().getS() > 0) {
          this.getPlayer().setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getS()));
          this.roomModel.setCurrentRoom(this.getRoomModel().queryRoom(this.getRoomModel().getCurrentRoom().getS()));
        }
        else if (this.getRoomModel().getCurrentRoom().getS() == 0) {
          throw new IllegalArgumentException("There is no room to the south");
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
          throw new IllegalArgumentException("There is no room to the east");
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
          throw new IllegalArgumentException("There is no room to the west");
        }
        else {
          // get specific of why path is blocked
          throw new IllegalArgumentException("The path is blocked\n");
        }
        break;
    }
  }

  public void takeItem(Item item) {
      if (this.roomModel.getCurrentRoom().hasItem(item)) {
        this.getPlayer().getInventory().getItems().add(item);
        this.roomModel.getCurrentRoom().removeItem(item);
      }
      else {
        throw new IllegalArgumentException("There is no item with that name in the current room.\n");
      }
  }

  public void dropItem(Item item) {
    if (this.player.getInventory().hasItem(item)) {
      this.player.getInventory().dropItem(item);
      this.roomModel.getCurrentRoom().addItem((Item) item);
    }
    else {
      throw new IllegalArgumentException("You don't have a ".concat(item.getName().concat(".\n")));
    }
  }

  public void useItem(Item item) {
    if (this.player.getInventory().hasItem(item)) {
      if (item.getUsesRemaining() > 0) {
        item.setUsesRemaining(item.getUsesRemaining() - 1);
        if (this.roomModel.getCurrentRoom().getMonster() != null) {
          attackMonster(item);
        }
        else if (this.roomModel.getCurrentRoom().getPuzzle() != null) {
          solvePuzzle(item);
        }
      } else {
        throw new IllegalArgumentException(item.getName().concat(" is out of uses.\n"));
      }
    }
    else {
      throw new IllegalArgumentException("You don't have a ".concat(item.getName().concat(".\n")));
    }
  }

  public void solvePuzzle(Item item) {
    if (this.roomModel.getCurrentRoom().getPuzzle() != null && this.roomModel.getCurrentRoom().getPuzzle().isActive()) {
      if (item.equals(this.roomModel.getCurrentRoom().getPuzzle().getSolution())) {
        this.roomModel.getCurrentRoom().getPuzzle().setActive(false);
        this.player.addPuzzle(this.roomModel.getCurrentRoom().getPuzzle());
        clearRoom(this.roomModel.getCurrentRoom());
      } else {
        throw new IllegalArgumentException("It had no effect.\n");
      }
    }
    else {
      throw new IllegalArgumentException("There is nothing here to use it on.\n");
    }
  }

  public void attackMonster(Item item) {
    if (this.roomModel.getCurrentRoom().getMonster() != null && this.roomModel.getCurrentRoom().getMonster().isActive()) {
      if (item.equals(this.roomModel.getCurrentRoom().getMonster().getSolution())) {
        this.roomModel.getCurrentRoom().getMonster().setActive(false);
        this.player.addMonster(this.roomModel.getCurrentRoom().getMonster());
        clearRoom(this.roomModel.getCurrentRoom());
      } else {
        throw new IllegalArgumentException("It had no effect.\n");
      }
    }
    else {
      throw new IllegalArgumentException("There is nothing here to use it on.\n");
    }
  }

  public void clearRoom(Room room) {
    if (this.roomModel.getCurrentRoom().getN() < 0) {
      this.roomModel.getCurrentRoom().setN(this.roomModel.getCurrentRoom().getN() * -1);
    }
    else if (this.roomModel.getCurrentRoom().getS() < 0) {
      this.roomModel.getCurrentRoom().setS(this.roomModel.getCurrentRoom().getS() * -1);
    }
    else if (this.roomModel.getCurrentRoom().getE() < 0) {
      this.roomModel.getCurrentRoom().setE(this.roomModel.getCurrentRoom().getE() * -1);
    }
    else if (this.roomModel.getCurrentRoom().getW() < 0) {
      this.roomModel.getCurrentRoom().setW(this.roomModel.getCurrentRoom().getW() * -1);
    }
  }

  public boolean gameOver() {
    if (this.player.getHealth() <= 0) {
      return true;
    }
    else if (this.roomModel.allClear()) {
      return true;
    }
    else {
      return false;
    }
  }

  public String endGame() {
    this.player.calculateScore();
    if (this.player.getHealth() <= 0) {
      return "Game Over!\nYour score was: ".concat(String.valueOf(this.player.getScore()));
    }
    else if (this.roomModel.allClear()) {
      return "You won!\nYour score was: ".concat(String.valueOf(this.player.getScore()));
    }
    else {
      return "You quit.\nYour score was: ".concat(String.valueOf(this.player.getScore()));
    }
  }

  public void takeDamage() {
    if (this.roomModel.getCurrentRoom().getMonster() != null) {
      this.player.setHealth(this.player.getHealth() + this.roomModel.getCurrentRoom().getMonster().getDamage());
    }
    else {
      throw new NoSuchElementException("");
    }
  }

  public void answer() {}

  public boolean validTarget() {
    return true;
  }
}
