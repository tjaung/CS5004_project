package gameelements;

import gameelements.Fixture;
import gameelements.Puzzle;
import gameelements.Item;

/**
 * Class for Room.
 */
public class Room {
  private String roomName;
  private int roomNumber;
  private String description;
  private int N;
  private int S;
  private int E;
  private int W;
  private Puzzle puzzle;
  private Item items;
  private Fixture fixtures;
  private String picture;

  /**
   * Constructor for Room. Takes in a lot of parameters to construct it.
   */
  public Room(
          String roomName,
          int roomNumber,
          String description,
          int N,
          int S,
          int E,
          int W,
          Puzzle puzzle,
          Item items,
          Fixture fixtures,
          String picture) {

    // Check parameters
    this.checkParameters(roomName, roomNumber, description, N, S, E, W, puzzle, items, fixtures, picture)
    this.roomName = roomName;
    this.description = description;
    this.N = N;
    this.S = S;
    this.E = E;
    this.W = W;
    this.puzzle = puzzle;
    this.items = items;
    this.picture = picture;
  }

  /**
   * Getter for the room description.
   *
   * @return desc - string of the room desc.
   */
  public String getRoomDescription() {
    return description;
  }

  /**
   * Getter for North direction.
   *
   * @return N - int of N room
   */
  public int getN() {
    return N;
  }

  /**
   * Getter for South direction.
   *
   * @return S - int of S room
   */
  public int getS() {
    return S;
  }

  /**
   * Getter for East direction.
   *
   * @return E - int of E room
   */
  public int getE() {
    return E;
  }

  /**
   * Getter for West direction.
   *
   * @return W - int of W room
   */
  public int getW() {
    return W;
  }

}