package gameelements;

import java.awt.geom.Point2D;

public class Player {
  private String name;
  private int score;
  private int health;
  private Point2D location;
  private Room currentRoom;
  private Inventory inventory;

  public Player() {
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

  public Point2D getLocation() {
    return location;
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

  public void setLocation(Point2D location) {
    this.location = location;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }
  
}
