package model;

import org.json.JSONObject;

import java.util.NoSuchElementException;

import gameelements.Item;
import gameelements.Monster;
import gameelements.Player;
import gameelements.Puzzle;
import gameelements.Room;

/**
 * Model in MVC schema. Has a database and handles logic.
 */
public class GameModel {
  RoomModel roomModel; // database
  Player player;
  private String string;
  private String imagePath;
  private String endTurnMessage;
  private boolean gameOver;
  private String descriptionPath;
  // inventory is contained within player


  /**
   * Constructor for the GameModel class.
   */
  public GameModel(String gameFileName) throws Exception {
    this.roomModel = new RoomModel(gameFileName);
    this.player = new Player();
    this.player.setCurrentRoom(this.roomModel.currentRoom);
    this.string = this.roomModel.getCurrentRoom().toString();
    this.imagePath = this.roomModel.getCurrentRoom().getPicture();
    this.endTurnMessage = "";
    this.descriptionPath = this.roomModel.getCurrentRoom().getDescription();
  }

  /**
   * Gets the player object.
   * 
   */
  public Player getPlayer() {
    return player;
  }


  /**
   * Gets the room model for the game
   */
  public RoomModel getRoomModel() {
    return roomModel;
  }

  /**
   * Moves the player 
   */
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
    // this.roomModel.currentRoom.toString()
    this.setString("You moved " + s + "\n" +
            this.roomModel.currentRoom.toString());
    this.endTurn();
//    this.setString("You moved " + s + "\n" +
//            this.roomModel.currentRoom.toString());
    // print room description in the panel
    //this.setString(this.roomModel.currentRoom.toString());
    //this.setString(this.roomModel.currentRoom.printItems());
  }

  /**
   * Makes the player take an item from the current room.
   * If the item is not in the room, an exception is thrown.
   */
  public void takeItem(Item item) {
    if (this.roomModel.getCurrentRoom().hasItem(item)) {
      this.getPlayer().getInventory().addItem(item);
      this.roomModel.getCurrentRoom().removeItem(item);
      this.setString(this.roomModel.currentRoom.toString());
    }
    else {
      throw new IllegalArgumentException("There is no item with that name in the current room.\n");
    }
    this.endTurn();
  }

  /**
   * Makes the player drop an item from their inventory into the current room.
   * If the player does not have the item, an exception is thrown.
   */
  public void dropItem(Item item) {
    if (this.player.getInventory().hasItem(item)) {
      this.player.getInventory().dropItem(item);
      this.roomModel.getCurrentRoom().addItem((Item) item);
      this.setString(this.roomModel.getCurrentRoom().toString());
    }
    else {
      throw new IllegalArgumentException("You don't have a ".concat(item.getName().concat(".\n")));
    }
    this.endTurn();
  }

  /**
   * Uses an item from the player's inventory. If the item is used on a monster or puzzle,
   */
  public void useItem(Item item) {
    if (this.player.getInventory().hasItem(item)) {
      if (item.getUsesRemaining() > 0) {
        item.setUsesRemaining(item.getUsesRemaining() - 1);
        Monster roomMonster = this.roomModel.getCurrentRoom().getMonster();
        Puzzle roomPuzzle = this.roomModel.getCurrentRoom().getPuzzle();
        Boolean isMonsterUsed = false;
        Boolean isPuzzleUsed = false;

        // if monster in room and active use on monster
        if (roomMonster != null && roomMonster.isActive()) {
          attackMonster(item);
          isMonsterUsed = true;
        } // if puzzle in room and active
        else if (roomPuzzle != null && roomPuzzle.isActive()) {
          solvePuzzle(item);
          isPuzzleUsed = true;
        }
        if (!isMonsterUsed && !isPuzzleUsed) {
          throw new IllegalArgumentException("There is nothing to use this on.\n");
        }
        else {
          this.setString(this.roomModel.getCurrentRoom().toString());
        }
      } else {
        throw new IllegalArgumentException(item.getName().concat(" is out of uses.\n"));
      }
    }
    else {
      throw new IllegalArgumentException("You don't have a ".concat(item.getName().concat(".\n")));
    }
    this.endTurn();
  }


  
  /**
   * Solves a puzzle by answering with the correct solution.
   */
  public void answerRiddle(String answer) {
    Puzzle puzzle = this.roomModel.getCurrentRoom().getPuzzle();

    if (puzzle != null && puzzle.isActive()) {
      if (answer.equalsIgnoreCase(puzzle.getSolution().getName())) {
        puzzle.setActive(false);
        this.player.addPuzzle(puzzle);
        clearRoom(this.roomModel.getCurrentRoom());
        this.setString(this.roomModel.getCurrentRoom().toString());
      } else {
        throw new IllegalArgumentException("It had no effect.\n");
      }
    }
    else {
      throw new IllegalArgumentException("There is no question here to answer.\n");
    }
    this.endTurn();
  }

   /**
   * Solves a puzzle using an item as the solution.
   */
  public void solvePuzzle(Item item) {
    Puzzle puzzle = this.roomModel.getCurrentRoom().getPuzzle();

    if (puzzle != null && puzzle.isActive()) {
      if (item.equals(puzzle.getSolution())) {
       puzzle.setActive(false);
        this.player.addPuzzle(puzzle);
        clearRoom(this.roomModel.getCurrentRoom());
      } else {
        throw new IllegalArgumentException("It had no effect.\n");
      }
    }
    else {
      throw new IllegalArgumentException("There is nothing here to use it on.\n");
    }
  }

  /**
   * Attacks a monster using an item.
   */
  public void attackMonster(Item item) {
    Monster monster = this.roomModel.getCurrentRoom().getMonster();
    if (monster != null && monster.isActive()) {
      if (item.equals(monster.getSolution())) {
        monster.setActive(false);
        this.player.addMonster(monster);
        clearRoom(this.roomModel.getCurrentRoom());
      } else {
        throw new IllegalArgumentException("It had no effect.\n");
      }
    }
    else {
      throw new IllegalArgumentException("There is nothing here to use it on.\n");
    }
  }

  /**
   * Clears a room 
   */
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
    this.roomModel.getCurrentRoom().setClear(true);
  }

  /**
   * Checks if the game is over.
   */
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

   /**
   * Ends the game
   */
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

  /**
   * Applies damage.
   */
  public boolean takeDamage() {
    if ((this.roomModel.getCurrentRoom().getMonster() != null) && this.roomModel.getCurrentRoom().getMonster().isActive()) {
      this.player.setHealth(this.player.getHealth() + this.roomModel.getCurrentRoom().getMonster().getDamage());
      return true;
    }
    else {
      this.setEndTurnMessage("");
      return false;
    }
  }

  /**
   * Saves the game
   */
  public String saveGame(String name) {
    String playerStr = this.player.parsePlayerToJSON();
    String invStr = this.player.getInventory().parseInventoryToJSON();
    String roomStr = this.roomModel.parseRoomsToJSON();
    String jsonStr = "{" + playerStr + invStr + roomStr + "}";
    String response = SaveFiles.saveToJSON(jsonStr, name);
    return response;
  }

   /**
   * Loads the game
   */
  public String loadGame(String name) {
    // check for save data
    try {
      this.roomModel = new RoomModel(name);
      String strJson = Parser.readJsonFile(name);
      JSONObject json = Parser.parseJsonString(strJson);
      this.player = Parser.parsePlayer(json, this.roomModel.getRoomList());
      return "Save data loaded successfully";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * Processes any changes to the model that should occur whenever a turn ends.
   * Updates the endTurnMessage if appropriate.
   */
  public void endTurn() {
    if (this.takeDamage()) {
      this.setEndTurnMessage(this.getRoomModel().getCurrentRoom().getMonster().getAttack());
    }
    if (this.gameOver()) {
      String byeMessage = this.endGame();
    }
  }

  public void setEndTurnMessage(String newMessage) {
    this.endTurnMessage = newMessage;
  }

  public String getEndTurnMessage() {
    // checks if player takes dmg
    // checks if game is over or player quits
    return this.endTurnMessage;
  }

  public void setString(String newStr) {
    this.string = newStr;
  }

  public String getString() {
    return this.string;
  }

  public boolean isGameOver() {
    return gameOver;
  }
}
