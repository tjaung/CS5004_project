package model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gameelements.Inventory;
import gameelements.Player;
import gameelements.Room;

/**
 * Reverse parser class is for saving game states.
 * It takes the list of all rooms and turns the data into a
 * JSON file like the input data.
 */
public class ReverseParser {

  public static String readPlayer(Player player) {
    JSONObject playerObject = new JSONObject();
    System.out.println(player.getCurrentRoom().toString());
    playerObject.put("name", player.getName());
    playerObject.put("score", player.getScore());
    playerObject.put("health", player.getHealth());
    playerObject.put("currentRoom", player.getCurrentRoom().getRoomNumber());
    playerObject.put("inventory", "Inventory");

    String playerString = "'player': " + playerObject.toJSONString() + ",";
    System.out.println(playerString);
    return playerString;
  }

  public static String readInventory(Inventory inventory) {
    String itemString = inventory.getItems().stream()
            .map(item -> item.getName())
            .collect(Collectors.joining(","));
    System.out.println(itemString);
    JSONObject inventoryObject = new JSONObject();
    inventoryObject.put("current_weight", inventory.getCurrWeight());
    inventoryObject.put("max_weight", inventory.getMaxWeight());
    inventoryObject.put("items", itemString);

    String inventoryString = "'inventory': " + inventoryObject.toJSONString() + ",";
    System.out.println(inventoryString);
    return inventoryString;
  }

  public static List<String> readRoom(List<Room> rooms) {
    List<String> roomList = new ArrayList<>();

    // loop all of the rooms
    for(Room room : rooms) {
      JSONObject roomObject = new JSONObject();

      // get attributes
      String monster = room.getMonster() != null ? room.getMonster().getName(): null;
      String puzzle = room.getPuzzle() != null ? room.getPuzzle().getName(): null;
      String fixtures = room.getFixtures() != null ? room.printFixtures() : null;
      String items = room.getItems() != null ? room.printItems() : null;

      roomObject.put("room_name", room.getRoomName());
      roomObject.put("room_number", room.getRoomNumber());
      roomObject.put("description", room.getRoomDescription());
      roomObject.put("N", room.getN());
      roomObject.put("S", room.getS());
      roomObject.put("E", room.getE());
      roomObject.put("W", room.getW());
      roomObject.put("puzzle", puzzle);
      roomObject.put("monster", monster);
      roomObject.put("items", items);
      roomObject.put("fixtures", fixtures);
      roomObject.put("picture", room.getPicture());

      String roomString = roomObject.toJSONString();
      roomList.add(roomString);
    }
    return roomList;
  }


}
