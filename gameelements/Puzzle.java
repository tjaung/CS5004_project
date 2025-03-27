package gameelements;

public class Puzzle {
  private String  name;
  private boolean active;
  private boolean affects_target;
  private Item solution;
  private int value;
  private String effects;
  private String description;
  private String target;
  private String picture;
  private boolean affects_player;

  public Puzzle(String name,
      boolean affects_target,
      boolean active,
      Item solution, int value,
                String effects, String description, String target, String picture, boolean affects_player) {
    this.name = name;
    this.affects_target = affects_target;
    this.active = active;
    this.affects_player = affects_player;
    this.solution = solution;
    this.value = value;
    this.effects = effects;
    this.description = description;
    this.target = target;
    this.picture = picture;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
  }

  public boolean affects_player() {
    return affects_player;
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
