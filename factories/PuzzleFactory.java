package factories;

import gameelements.Item;
import gameelements.Puzzle;

public class PuzzleFactory {
  public static Puzzle create(boolean affects_target, boolean affects_player, boolean active, Item solution, int value,
                              String effects, String description, Object target, String picture) {
    return new Puzzle(affects_target, affects_player, active, solution, value,
     effects, description, target, picture);
  }
}
