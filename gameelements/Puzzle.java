package gameelements;

public class Puzzle {
  private boolean active;
  private boolean affects_target;
  private boolean affects_player;
  private Item solution;
  private int value;
  private String effects;
  private String description;
  private Object target;
  private String picture;

  public boolean isActive() {
    return active;
  }

  public boolean affects_target() {
    return affects_target;
  }

  public Item getSolution() {
    return solution;
  }

  public int getValue() {
    return value;
  }

  public String getEffects() {
    return effects;
  }

  public String getDescription() {
    return description;
  }

  public Object getTarget() {
    return target;
  }

  public String getPicture() {
    return picture;
  }
}
