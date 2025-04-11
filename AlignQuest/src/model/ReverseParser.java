package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gameelements.Fixture;
import gameelements.IRoomElement;
import gameelements.Inventory;
import gameelements.Item;
import gameelements.Monster;
import gameelements.Player;
import gameelements.Puzzle;
import gameelements.Room;

/**
 * Reverse parser class is for saving game states.
 * It takes the list of all rooms and turns the data into a
 * JSON file like the input data.
 */
public class ReverseParser {

  public static String readPlayer(Player player) {
    JSONObject playerObject = new JSONObject();
    playerObject.put("name", player.getName());
    playerObject.put("score", player.getScore());
    playerObject.put("health", player.getHealth());
    playerObject.put("currentRoom", player.getCurrentRoom().getRoomNumber());
    playerObject.put("inventory", "Inventory");

    return "\"player\": " + playerObject.toJSONString() + ",";
  }

  public static String readInventory(Inventory inventory) {
    JSONArray jsonArray = new JSONArray();
    List<Item> items = inventory.getItems();

    // make a json of all of the items in inventory
    for (Item item : items) {
      JSONObject jsonItem = new JSONObject();
      jsonItem.put("name", item.getName());
      jsonItem.put("weight", item.getWeight());
      jsonItem.put("max_uses", item.getMaxUses());
      jsonItem.put("uses_remaining", item.getUsesRemaining());
      jsonItem.put("value", item.getValue());
      jsonItem.put("when_used", item.getWhenUsed());
      jsonItem.put("description", item.getDescription());
      jsonItem.put("picture", item.getPicture());

      jsonArray.add(jsonItem);
    }
    JSONObject inventoryObject = new JSONObject();
    inventoryObject.put("current_weight", inventory.getCurrWeight());
    inventoryObject.put("max_weight", inventory.getMaxWeight());
    inventoryObject.put("items", jsonArray);

    return "\"inventory\": " + inventoryObject.toJSONString() + ",";
  }

  public static String readSingleItem(Item item) {
    JSONObject itemObject = new JSONObject();
    itemObject.put("name", item.getName());
    itemObject.put("weight", item.getWeight());
    itemObject.put("value", item.getValue());
    itemObject.put("max_uses", item.getMaxUses());
    itemObject.put("uses_remaining", item.getUsesRemaining());
    itemObject.put("when_used", item.getWhenUsed());
    itemObject.put("description", item.getDescription());
    itemObject.put("picture", item.getPicture());

    return itemObject.toJSONString();
  }

  public static String readItems(Inventory inventory, List<Room> rooms) {

    // loop inventory items
    List<Item> items = inventory.getItems();
    List<String> itemStrings = new ArrayList<>();
    for (Item item : items) {
      itemStrings.add(readSingleItem(item));
    }

    // loop rooms
    for(Room room : rooms) {
      List<IRoomElement> roomItems = room.getItems();
      for (IRoomElement roomItem : roomItems) {
        Item item = (Item) roomItem;
        itemStrings.add(readSingleItem(item));
      }
    }
    return "\"items\": " + itemStrings + ",";

  }

  public static String readRoom(List<Room> rooms) {
    List<String> roomList = new ArrayList<>();
    List<String> monsterList = new ArrayList<>();
    List<String> puzzleList = new ArrayList<>();
    List<String> fixturesList = new ArrayList<>();
    List<String> itemList = new ArrayList<>();

    // loop all of the rooms
    for(Room room : rooms) {
      JSONObject roomObject = new JSONObject();

      // get attributes
      String monster = room.getMonster() != null ? room.getMonster().getName(): null;
      String puzzle = room.getPuzzle() != null ? room.getPuzzle().getName(): null;
      String fixtures = room.getFixtures() != null ? room.printFixtures() : null;
      String items = room.getItems() != null ? room.printItems() : null;

      // if room has game elements parse them
      if(fixtures != null) {
        for(IRoomElement fixture : room.getFixtures()) {
          Fixture fix = (Fixture) fixture;
          fixturesList.add(readFixture(fix));
        }
      }
      if(monster != null) {
        Monster monsterObj = (Monster) room.getMonster();
        monsterList.add(readMonster(monsterObj));
      }
      if(puzzle != null) {
        Puzzle puzzleObj = (Puzzle) room.getPuzzle();
        puzzleList.add(readPuzzle(puzzleObj));
      }
      if(items != null) {
        for(IRoomElement item : room.getItems()) {
          Item i = (Item) item;
          itemList.add(readSingleItem(i));
        }
      }

      roomObject.put("room_name", room.getName());
      roomObject.put("room_number", room.getRoomNumber());
      roomObject.put("description", room.getDescription());
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
    String monsters = "\"monsters\": " + monsterList.toString() + ",";
    String puzzles = "\"puzzles\": " + puzzleList.toString() + ",";
    String fixtures = "\"fixtures\": " + fixturesList.toString() + ",";
    String items = "\"items\": " + itemList.toString();
    return "\"rooms\": " + roomList.toString() + "," + monsters + puzzles + fixtures + items;
  }

  public static String readPuzzle(Puzzle puzzle) {
    List<String> puzzleList = new ArrayList<>();
    JSONObject puzzleObject = new JSONObject();

    puzzleObject.put("name", puzzle.getName());
    puzzleObject.put("active", puzzle.isActive());
    puzzleObject.put("affects_target", puzzle.affects_target());
    puzzleObject.put("affects_player", puzzle.affects_player());
    puzzleObject.put("solution", puzzle.getSolution().getName());
    puzzleObject.put("value", puzzle.getValue());
    puzzleObject.put("effects", puzzle.getEffects());
    puzzleObject.put("target", puzzle.getTarget());
    puzzleObject.put("description", puzzle.getDescription());
    puzzleObject.put("picture", puzzle.getPicture());

    return puzzleObject.toJSONString();
  }

  public static String readFixture(Fixture fixture) {
    List<String> fixtureList = new ArrayList<>();
    JSONObject fixtureObject = new JSONObject();
    String puzzle = fixture.getPuzzle() != null ? fixture.getPuzzle().getName() : null;

    fixtureObject.put("name", fixture.getName());
    fixtureObject.put("weight", fixture.getWeight());
    fixtureObject.put("state", fixture.getState());
    fixtureObject.put("puzzle", puzzle);
    fixtureObject.put("description", fixture.getDescription());
    fixtureObject.put("picture", fixture.getPicture());

    return fixtureObject.toJSONString();
  }

  public static String readMonster(Monster monster) {
    List<String> monsterList = new ArrayList<>();
    JSONObject monsterObject = new JSONObject();
    monsterObject.put("name", monster.getName());
    monsterObject.put("active", monster.isActive());
    monsterObject.put("affects_target", monster.affectsTarget());
    monsterObject.put("affects_player", monster.affectsPlayer());
    monsterObject.put("solution", monster.getSolution().getName());
    monsterObject.put("value", monster.getValue());
    monsterObject.put("description", monster.getDescription());
    monsterObject.put("effects", monster.getEffects());
    monsterObject.put("damage", monster.getDamage());
    monsterObject.put("target", monster.getTarget());
    monsterObject.put("can_attack", monster.canAttack());
    monsterObject.put("attack", monster.getAttack());
    monsterObject.put("picture", monster.getPicture());
//    monsterObject.put("health", monster.isAffectsTarget());

    return monsterObject.toJSONString();

  }




}
