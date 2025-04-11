package gameelements;

import java.util.ArrayList;
import java.util.List;

import model.ReverseParser;

/**
 * The Player class
 */
public class Player {
  
  private String name;              
  private int score;             
  private int health;               
  private Room currentRoom;         
  private Inventory inventory;     
  private List<Puzzle> solvedPuzzles;  
  private List<Monster> defeatedMonsters; 

  /**
   * Constructor. 
   */
  public Player() {
    this.health = 100;
    this.inventory = new Inventory(10, 0, "./");
    this.solvedPuzzles = new ArrayList<>();
    this.defeatedMonsters = new ArrayList<>();
  }

  /**
   * Gets the name of the player.
   * 
   * @return The name of the player.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the score of the player.
   * 
   * @return The score of the player.
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Gets the current room the player is in.
   * 
   * @return The current room.
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Gets the health of the player.
   * 
   * @return The health of the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets the inventory of the player.
   * 
   * @return The player's inventory.
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Sets the player's inventory with new parameters and adds items to it.
   */
  public void setInventory(int maxWeight, int currentWeight, String path, List<Item> itemList) {
    this.inventory = new Inventory(maxWeight, currentWeight, path);
    for(Item item : itemList) {
      this.inventory.addItem(item);
    }
  }

  /**
   * Sets the name of the player.
   * 
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the score of the player.
   * 
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Sets the health of the player.
   * 
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Sets the current room the player is in.
   * 
   */
  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  /**
   * Sets the inventory of the player.
   * 
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Converts the player object to a JSON string.
   * 
   */
  public String parsePlayerToJSON() {
    String playerStr = ReverseParser.readPlayer(this);
    return playerStr;
  }

  /**
   * Adds a monster to the list of defeated monsters.
   * 
   */
  public void addMonster(Monster monster) {
    this.defeatedMonsters.add(monster);
  }

  /**
   * Adds a puzzle to the list of solved puzzles.
   * 
   */
  public void addPuzzle(Puzzle puzzle) {
    this.solvedPuzzles.add(puzzle);
  }

  /**
   * Calculates the player's score
   */
  public void calculateScore() {
    int score = 0;
    for (Item i : this.getInventory().getItems()) {
      score+= i.getValue();
    }
    for (Monster j : this.defeatedMonsters) {
      score += j.getValue();
    }
    for (Puzzle k : this.solvedPuzzles) {
      score += k.getValue();
    }
    this.setScore(score);
  }

  /**
   * Returns a string describing the player's health status.
   */
  public String toString() {
    if (this.health <= 0 )
      return "You fell asleep!";
    if(this.health < 40)
      return "Your health is VERY LOW, and you're WOOZY!\n";
    if(this.health < 70)
      return "Your health is LOW, and you're FATIGUED!\n";
    return "Your health is HIGH, and you're AWAKE!\n";
  }

}
