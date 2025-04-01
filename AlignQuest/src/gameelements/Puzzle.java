package gameelements;


/**
 * The Puzzle class
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
   * 
   * @return The name of the puzzle.
   */
  public String getName() {
    return name;
  }

  /**
   * Checks if the puzzle is active.
   */ 
  public boolean isActive() {
    return active;
  }

  /**
   * Checks if the puzzle impacts player.
   */
  public boolean affects_player() {
    return affects_player;
  }

   /**
   * Checks if the puzzle impacts target.
   */
  public boolean affects_target() {
    return affects_target;
  }

  /**
   * Gets the solution to the puzzle.
   */
  public IRoomElement getSolution() {
    return solution;
  }

   /**
   * Gets the score for solving.
   */
  public int getValue() {
    return value;
  }

  /**
   * Gets the effects of the puzzle.
   */
  public String getEffects() {
    return effects;
  }

  /**
   * Gets the description of the puzzle.
   */
  public String getDescription() {
    return description;
  }

   /**
   * Gets target of the puzzle.
   */
  public Object getTarget() {
    return target;
  }

   /**
   * Gets the picture.
   */
  public String getPicture() {
    return picture;
  }

  /**
   * Sets the puzzle status.
   */
  public void setActive(boolean bool) {
    this.active = bool;
  }
}
