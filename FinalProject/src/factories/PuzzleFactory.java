package factories;

import gameelements.Item;
import gameelements.Puzzle;

public class PuzzleFactory {
  public static Puzzle create(String name,
                              boolean affects_target,
                              boolean active,
                              Item solution, int value,
                              String effects, String description, String target, String picture, boolean affects_player) {
    return new Puzzle(name, affects_target, active, solution, value, effects, description, target, picture, affects_player);
  }
}
