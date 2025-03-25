import java.awt.geom.Point2D;

import gameelements.Inventory;
import gameelements.Room;
import gameelements.playerClass;

class playerFactory {

  public playerClass create(String shapeProduct, String name,
                            int score, int health,
                            Point2D location, Room currentRoom,
                            Inventory inventory) {
    if (shapeProduct.equalsIgnoreCase("player")) {

      return new playerClass(name, score, health, location,
              currentRoom, inventory);
    }
    return null;
  }
}
