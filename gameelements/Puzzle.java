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

  public Puzzle(boolean affects_target, boolean affects_player, boolean active, Item solution, int value,
                String effects, String description, Object target, String picture) {
    this.affects_target = affects_target;
    this.affects_target = affects_target;
    this.active = active;
    this.solution = solution;
    this.value = value;
    this.effects = effects;
    this.description = description;
    this.target = target;
    this.picture = picture;
  }

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
