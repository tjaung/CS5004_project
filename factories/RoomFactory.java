package factories;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import gameelements.Puzzle;
import gameelements.Fixture;
import gameelements.Item;
import gameelements.Room;
import gameelements.Monster;

/**
 * Factory for Fixture class.
 */
public class RoomFactory {

  public static Room create(String roomName,
                            int roomNumber,
                            String description,
                            int N,
                            int S,
                            int E,
                            int W,
                            Monster monster,
                            Puzzle puzzle,
                            Item items,
                            Fixture fixtures,
                            String picture) {
    // Assuming that puzzle, Item, and Fixture will already be instantiated for construction
    return new Room(
            roomName,
            roomNumber,
            description,
            N,
            S,
            E,
            W,
            monster,
            puzzle,
            items,
            fixtures,
            picture);
  }
}