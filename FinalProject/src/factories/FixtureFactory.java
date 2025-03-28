package factories;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import gameelements.Puzzle;
import gameelements.Fixture;

/**
 * Factory for Fixture class.
 */
public class FixtureFactory {

  public static Fixture create(String name, int weight, String description,
                               String picture,
                               Boolean state,
                               Puzzle puzzle
                               ) {
    // Assuming that puzzle and state will already be instantiated for construction
    return new Fixture(name, weight, description, picture, state, puzzle);
  }
}
