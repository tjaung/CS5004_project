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

  public static Fixture create(int weight,
                               Puzzle puzzle,
                               State state,
                               String description,
                               String picture) {
    // Assuming that puzzle and state will already be instantiated for construction
    return new Fixture(weight, puzzle, state, description, picture);
  }
}
