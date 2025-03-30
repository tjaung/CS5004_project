package gameelements;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private int score;
  private int health;
  private Room currentRoom;
  private Inventory inventory;
  private List<Puzzle> solvedPuzzles;
  private List<Monster> defeatedMonsters;

  public Player() {
    this.health = 100;
    this.inventory = new Inventory(10, 0, "./");
    this.solvedPuzzles = new ArrayList<>();
    this.defeatedMonsters = new ArrayList<>();
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

  public void addMonster(Monster monster) {
    this.defeatedMonsters.add(monster);
  }

  public void addPuzzle(Puzzle puzzle) {
    this.solvedPuzzles.add(puzzle);
  }

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
