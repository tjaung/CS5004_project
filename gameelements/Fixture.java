package gameelements;

import gameelements.Puzzle;

/**
 * Class for a Fixture concrete Object.
 */
public class Fixture {
  private int weight;
  private Puzzle puzzle = null;
  private State state = null;
  private String description;
  private String picture;

  /**
   * Constructor for Fixture. takes in a weight, puzzle, state, desc, and picture.
   *
   * @param weight - int of the fixtures weight from 0 to inf.
   * @param puzzle - an associated puzzle with fixture of type Puzzle.
   * @param state - the current state of the fixture (not needed right now).
   * @param description - a string desc of the fixture.
   * @param picture - a string path to the corresponding asset.
   */
  public Fixture(int weight,
                 Puzzle puzzle,
                 State state,
                 String description,
                 String picture) {
    // Check parameters for missingness
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be non-null and greater than 0.");
    }
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("Description must not be empty.");
    }

    // instantiate attributes
    this.weight = weight;
    this.puzzle = puzzle;
    this.state = state;
    this.description = description;
    this.picture = picture;
  }

  /**
   * Getter for weight.
   *
   * @return weight of fixture.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Setter for weight.
   *
   * @param newWeight int of new weight to set.
   */
  public void setWeight(int newWeight) {
    this.weight = newWeight;
  }

  /**
   * Getter for Puzzle.
   *
   * @return puzzle associated with fixture.
   */
  public Puzzle getPuzzle() {
    return puzzle;
  }

  /**
   * Setter for Puzzle. Might not be necessary.
   *
   * @param newPuzzle of Puzzle type.
   */
  public void setPuzzle(Puzzle newPuzzle) {
    this.puzzle = newPuzzle;
  }


  /**
   * Getter for State of fixture.
   *
   * @return current state of fixture.
   */
  public State getState() {
    return state;
  }

  /**
   * Set the current state to a new state.
   *
   * @param newState the new state to be updated to.
   */
  public void setState(State newState) {
    this.state = newState;
  }

  /**
   * Getter for description of the fixture.
   *
   * @return string of the fixture description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter for a new description.
   *
   * @param newDesc string of the new Description.
   */
  public void setDescription(String newDesc) {
    this.description = newDesc;
  }

  /**
   * Getter for picture asset of fixture.
   *
   * @return string of the path to the picture asset.
   */
  public String getPicture() {
    return picture;
  }

  /**
   * Setter for new picture asset.
   *
   * @param newPicture string path to new pic.
   */
  public void setPicture(String newPicture) {
    this.picture = newPicture;
  }

}