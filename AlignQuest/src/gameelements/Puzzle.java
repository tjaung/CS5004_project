package gameelements;

/**
 * Puzzle class.
 */
public class Puzzle implements IRoomElement{
  private String  name;
  private boolean active;
  private boolean affects_target;
  private IRoomElement solution;
  private int value;
  private String effects;
  private String description;
  private String target;
  private String picture;
  private boolean affects_player;


  /**
   * Constructs a new Puzzle object with the specified attributes.
   */
  public Puzzle(String name,
                boolean affects_target,
                boolean active,
                IRoomElement solution, int value,
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

  /**
   * Gets the name of the puzzle.
   * @return The name of the puzzle.
   */
  public String getName() {
    return name;
  }

  /**
   * Checks if the puzzle is active.
   * @return
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Checks if the puzzle affects the player.
   * @return
   */
  public boolean affects_player() {
    return affects_player;
  }

  /**
   * Checks if the puzzle affects a target.
   * @return
   */
  public boolean affects_target() {
    return affects_target;
  }

  /**
   *
   * @return
   */
  public IRoomElement getSolution() {
    return solution;
  }

  /**
   *
   * @return
   */
  public int getValue() {
    return value;
  }

  /**
   *
   * @return
   */
  public String getEffects() {
    return effects;
  }

  /**
   *
   * @return
   */
  public String getDescription() {
    return description;
  }

  /**
   *
   * @return
   */
  public Object getTarget() {
    return target;
  }

  /**
   *
   * @return
   */
  public String getPicture() {
    return picture;
  }

  /**
   *
   * @param bool
   */
  public void setActive(boolean bool) {
    this.active = bool;
  }
}
