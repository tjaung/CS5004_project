package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameelements.Fixture;
import gameelements.IRoomElement;
import gameelements.Inventory;
import gameelements.Item;
import gameelements.Player;
import gameelements.Room;

/**
 * Parser class takes the JSON data and creates the game world
 * by generating rooms and game elements based on the JSON data.
 */
public class Parser {

  /**
   * Reads the initial json file and converts to a string.
   *
   * @param filePath local json file path
   * @return string of the json data
   * @throws Exception any errors thrown
   */
  public static String readJsonFile(String filePath) throws Exception {
     try{
       return Files.readString(Paths.get(filePath));
     } catch (IOException e) {
//       String message = e.getMessage();
       throw new Exception("Error reading json file");
     }
  }

  /**
   * Takes the string json data and converts to real json.
   *
   * @param jsonData string json data
   * @return json data
   * @throws Exception any errors thrown
   */
  public static JSONObject parseJsonString(String jsonData) throws Exception {
    try{
      return new JSONObject(jsonData);
    } catch (JSONException e) {
      throw new Exception("Error parsing json string");
    }
  }

  /**
   * Returns a list of all items in the game.
   *
   * @param itemsArray array from json data
   * @return list of items
   */
  public static List<IRoomElement> parseItems(JSONArray itemsArray){
    if(itemsArray == null){
      throw new IllegalArgumentException("itemsArray is null");
    }

    // init empty item array
    List<IRoomElement> itemList = new ArrayList<>();
    if (itemsArray.isEmpty()){
      return itemList;
    }

    // loop parameter item array
    for(int i=0; i < itemsArray.length(); i++){
      JSONObject itemJson = itemsArray.getJSONObject(i);

      // cast each value to proper data type
      String name = itemJson.getString("name");
      int weight = itemJson.getInt("weight");
      int maxUses = itemJson.getInt("max_uses");
      int usesRemaining = itemJson.getInt("uses_remaining");
      int value = itemJson.getInt("value");
      String whenUsed = itemJson.getString("when_used");
      String description = itemJson.getString("description");
      String picture = itemJson.isNull("picture") ? "generic_item.png" : itemJson.getString("picture");

      // create concrete item instance
      Item item = new Item(weight,value,name,maxUses,usesRemaining,whenUsed,description,picture);
      // add item to output list
      itemList.add(item);
    }
    return itemList;
  }

  /**
   * returns a list of all monsters in the game.
   *
   * @param monsterArray array from json data
   * @return list of all monsters
   */
  public static List<IRoomElement> parseMonster(JSONArray monsterArray, List<IRoomElement> itemList) {
    if (monsterArray == null || itemList == null){
      throw new IllegalArgumentException("One or more arguments is null");
    }
    List<gameelements.IRoomElement> monsterList = new ArrayList<>();

    // loop parameter monster array
    for (int i = 0; i < monsterArray.length(); i++) {
      JSONObject monsterJson = monsterArray.getJSONObject(i);

      // cast to proper data types
      String name = monsterJson.getString("name");
      boolean active = monsterJson.getBoolean("active");
      boolean affectsTarget = monsterJson.getBoolean("affects_target");
      boolean affectsPlayer = monsterJson.getBoolean("affects_player");
      IRoomElement solution = getSolution(monsterJson, itemList);
      int value = monsterJson.getInt("value");
      String description = monsterJson.optString("description");
      String effects = monsterJson.optString("effects");
      int damage = monsterJson.getInt("damage");
      String target = monsterJson.optString("target");
      boolean canAttack = monsterJson.has("can_attack") && monsterJson.getBoolean("can_attack");
      String attack = monsterJson.getString("attack");
      String picture = monsterJson.isNull("picture") ? "generic_monster.png" : monsterJson.getString("picture");
      int health = monsterJson.has("health") ? monsterJson.getInt("health") : 0;

      // create concrete instance of monster
      gameelements.Monster monster = new gameelements.Monster(name, active, affectsTarget, affectsPlayer, solution, value, description,
              effects, damage, target, canAttack, attack, picture, health);
      monsterList.add(monster);
    }
    return monsterList;
  }

  /**
   * returns a list of all puzzles in the game.
   *
   * @param puzzleArray json array of puzzles
   * @param itemList list of all items since some puzzles need items to solve
   * @return list of all puzzles
   */
  public static List<IRoomElement> parsePuzzle(JSONArray puzzleArray, List<IRoomElement> itemList){
    if (puzzleArray == null || itemList == null){
      throw new IllegalArgumentException("One or more arguments is null");
    }
    List<gameelements.IRoomElement> puzzleList = new ArrayList<>();

    // loop parameter puzzle array
    for(int i=0; i < puzzleArray.length(); i++){
      JSONObject puzzleJson = puzzleArray.getJSONObject(i);

      // cast to proper data types
      String name = puzzleJson.getString("name");
      boolean active = puzzleJson.getBoolean("active");
      boolean affects_targets = puzzleJson.getBoolean("affects_target");
      boolean affects_player = puzzleJson.getBoolean("affects_player");
      IRoomElement solution = getSolution(puzzleJson, itemList);

      int value = puzzleJson.getInt("value");
      String description = puzzleJson.getString("description");
      String effects = puzzleJson.getString("effects");
      String target = puzzleJson.getString("target");
      String picture = puzzleJson.isNull("picture") ? "generic_puzzle.png" : puzzleJson.getString("picture");
      gameelements.Puzzle puzzle = new gameelements.Puzzle(
              name,
              affects_targets,
              active,
              solution,
              value,
              effects,
              description,
              target,
              picture,
              affects_player);
      puzzleList.add(puzzle);
    }
    return puzzleList;
  }

  /**
   * returns list of all fixtures in game.
   *
   * @param fixtureArray json array of fixture data
   * @param puzzleList list of puzzles since some fixtures have puzzles
   * @return list of all fixtures
   */
  public static List<IRoomElement> parseFixture(JSONArray fixtureArray, List<IRoomElement> puzzleList){
    if (fixtureArray == null || puzzleList == null){
      throw new IllegalArgumentException("One or more arguments is null");
    }
    List<gameelements.IRoomElement> fixturesList = new ArrayList<>();

    // loop param array of fixtures
    for(int i=0; i < fixtureArray.length(); i++){
      JSONObject fixtureJson = fixtureArray.getJSONObject(i);

      // cast to proper data types
      String name = fixtureJson.getString("name");
      int weight = fixtureJson.getInt("weight");
      String description = fixtureJson.getString("description");
      String picture = fixtureJson.isNull("picture") ? null : fixtureJson.getString("picture");
      // states is set to boolean as placeholder for now
      boolean state = fixtureJson.isNull("states") ? true : fixtureJson.getBoolean("states");
      IRoomElement puzzle = null;

      // query for correct puzzle
      if (fixtureJson.has("puzzle")) {
        String puzzleName = fixtureJson.isNull("puzzle") ? null : fixtureJson.getString("puzzle").toLowerCase();
        if (puzzleName != null) {
          puzzle = puzzleList.stream()
                  .filter(p -> p.getName().toLowerCase().equalsIgnoreCase(puzzleName))
                  .findFirst().get();
        }
      }

      // create fixture and add to list
      gameelements.Fixture fixture = new Fixture(name,weight,description,picture,state,puzzle);
      fixturesList.add(fixture);
    }
    return fixturesList;
  }

  /**
   * helper function to query game elements for a game element.
   * i.e. a given room has a fixture named "Computer". This queries
   * the list of all fixtures for "Computer" and returns it.
   *
   * @param room room its in
   * @param key key from json data
   * @param elementArray list of objects corresponding to key
   * @return single game object
   */
  private static List<IRoomElement> getRoomGameElements(JSONObject room, String key, List<IRoomElement> elementArray) {
    // init list to return
    List<IRoomElement> gameObjects = new ArrayList<IRoomElement>();

    // get the game element that the room needs
    if (!room.isNull(key)) {
      String roomEle = room.getString(key).toLowerCase();

      // if the game element has commas, it is probably a list of elements
      List<String> eleList = Arrays.asList(roomEle.split(", "));

      for(String ele : eleList){
        // query the list of elements
        IRoomElement gameObject = elementArray.stream()
                .filter(e -> e.getName().toLowerCase().equalsIgnoreCase(ele))
                .findFirst()
                .orElse(null);
        // add queried element to output list
        gameObjects.add(gameObject);
      }
    }
    return gameObjects;
  }

  
  /**
   * helper function to query solutions
   * finds the solution based on input
   */
  private static IRoomElement getSolution(JSONObject eleJson, List<IRoomElement> itemList) {
    // get the solution
    IRoomElement solution = null;

    if (eleJson.has("solution")) {
      String answer = eleJson.getString("solution");
      // solution condition 1: it's a pw and needs a string solution
      if(answer.startsWith("'") && answer.endsWith("'")) {
        String formatted_solution = answer.substring(1, answer.length() - 1);
        // create a new empty item that holds the string solution
        solution = new Item(0,0, formatted_solution, 1,1,"", "", "");
        itemList.add(solution);
      }
      // solution condition 2: it's an item puzzle and it needs a specific item
      else {
        // query the item list
        solution = itemList.stream()
                .filter(item -> item.getName().toLowerCase().equals(answer.toLowerCase()))
                .findFirst()
                .get();
      }
    }
    return solution;
  }

  /**
   * Creates all the rooms and returns the list of rooms.
   *
   * @param jsonObject full json data
   * @return list of all rooms with its game elements
   */
  public static List<Room> parseRooms(JSONObject jsonObject){
    if (jsonObject == null) {
      throw new IllegalArgumentException("JSON argument is null");
    }
    if (jsonObject.isEmpty()) {
      throw new IllegalArgumentException("JSON argument is empty");
    }
    List<Room> roomList = new ArrayList<>();
    JSONArray roomArray = jsonObject.getJSONArray("rooms");

    // create lists of game elements
    JSONArray itemJson = jsonObject.getJSONArray("items");
    List<IRoomElement> items = parseItems(itemJson);

    // create list of puzzles
    JSONArray puzzleJson = jsonObject.getJSONArray("puzzles");
    List<IRoomElement> puzzles = parsePuzzle(puzzleJson, items);

    // create list of fixtures
    JSONArray fixtureJson = jsonObject.getJSONArray("fixtures");
    List<IRoomElement> fixtures = parseFixture(fixtureJson, puzzles);

    // create list of monsters
    JSONArray monsterJson = jsonObject.isNull("monsters") ? null : jsonObject.getJSONArray("monsters");
    List<IRoomElement> monsters = monsterJson == null ? null : parseMonster(monsterJson, items);

    // create rooms
    for(int i=0; i < roomArray.length(); i++){
      JSONObject roomJson = roomArray.getJSONObject(i);
      String name = roomJson.getString("room_name");
      String description = roomJson.getString("description");
      int roomNumber = roomJson.getInt("room_number");
      int N = roomJson.getInt("N");
      int S = roomJson.getInt("S");
      int E = roomJson.getInt("E");
      int W = roomJson.getInt("W");
      String picture = roomJson.isNull("picture") ? "generic_location.png" : roomJson.getString("picture");

      // query correct game elements for each room
      List<IRoomElement> puzzle = getRoomGameElements(roomJson, "puzzle", puzzles);
      List<IRoomElement> item = getRoomGameElements(roomJson, "items", items);
      List<IRoomElement> fixture = getRoomGameElements(roomJson, "fixtures", fixtures);
      List<IRoomElement> monster = getRoomGameElements(roomJson, "monster", monsters);

      // create new room instance
      Room room = new Room(name,roomNumber,description,N,S,E,W,monster,puzzle, item, fixture, picture);
      roomList.add(room);
    }
    return roomList;
  }

   /**
   * helper function to query players
   * finds the solution based on input
   */
  public static Player parsePlayer(JSONObject jsonObject, List<Room> roomList) {
    if(jsonObject.isNull("player")) {
      return null;
    }
    JSONObject playerJson = (JSONObject) jsonObject.get("player");
    String name = playerJson.isNull("name") ? null : playerJson.getString("name");
    int score = playerJson.getInt("score");
    int health = playerJson.getInt("health");
    // query for current room
    int currRoomNumber = playerJson.getInt("currentRoom");
    Room currentRoom = roomList.stream()
            .filter(room -> room.getRoomNumber() == currRoomNumber)
            .findFirst()
            .get();
    Player player = new Player();
    player.setName(name);
    player.setScore(score);
    player.setHealth(health);

    // get inventory
    JSONObject inventoryJson = (JSONObject) jsonObject.get("inventory");
    player.setInventory(parseInventory(inventoryJson));

    return player;
  }

  /**
   * parses the inventory
   */
  public static Inventory parseInventory(JSONObject invObj) {
//    JSONObject invObj = (JSONObject) jsonObject.get("inventory");

    int maxWeight = invObj.getInt("max_weight");
    int currWeight = invObj.getInt("current_weight");
    String picture = !invObj.isNull("picture") ? invObj.getString("picture") : "./";

    Inventory inventory = new Inventory(maxWeight, currWeight, picture);

    // get items
    JSONArray items = invObj.getJSONArray("items");
    List<IRoomElement> invItems = parseItems(items);
    for (IRoomElement item : invItems) {
      Item invItem = (Item) item;
      inventory.addItem(invItem);
    }
    return inventory;
  }

  public static void loadSaveData(JSONObject jsonObject) {


  }

}




