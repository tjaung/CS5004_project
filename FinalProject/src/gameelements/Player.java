package gameelements;

import java.awt.geom.Point2D;

import model.ReverseParser;

public class Player {
  private String name;
  private int score;
  private int health;
  private Room currentRoom;
  private Inventory inventory;

  public Player() {
    this.inventory = new Inventory(10, 0, "./");
  }

  public String getName() {
    return this.name;
  }

  public int getScore() {
    return this.score;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public int getHealth() {
    return health;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public String parsePlayerToJSON() {
    String playerStr = ReverseParser.readPlayer(this);
    return playerStr;
  }
}
